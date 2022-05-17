/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hardemic.app.useCases;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.hardemic.app.UtilApp;

import com.hardemic.app.UtilLooca;
import com.hardemic.app.entities.Computador;
//import com.hardemic.app.entities.Log;
import com.hardemic.app.services.Slack;

import java.util.List;
import java.util.Scanner;

public class Monitor {

    // Singletown
    private final Looca looca = new Looca();
    private final UtilLooca util = new UtilLooca();
    private final Slack slack = new Slack();
    private final Scanner leitor = new Scanner(System.in);
    
    // Repositories
    
    private final LogUseCase logUseCase = new LogUseCase();
    private final ComputadorUseCase computadorUseCase = new ComputadorUseCase();

    // Grupos looca
    private Sistema sistema;
    private Memoria memoria;
    private Processador processador;
    private DiscosGroup grupoDeDiscos;

    // Dados do usuário logado
    private Integer fkEmpresa;
    private Integer fkComputador;

    // Contador para alertas no slack
    private Integer quantidadeAlertasDisco = 0;
    private Integer quantidadeAlertasMemoria = 0;
    private Integer quantidadeAlertasCPU = 0;
    private Integer inicializado = 0;

    public Monitor() {
    }
    
    public Monitor(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public Looca getLooca() {
        return looca;
    }

    public void init() throws InterruptedException, Exception {
        
        try {
            inicializado++;
            List<Computador> computadores = computadorUseCase.findByHostname(util.getHostName());

            if (!computadores.isEmpty()) {
                this.fkComputador = computadores.get(0).getId();
                
                if(inicializado == 1){
                    slack.infoMessage(":computer: *Novo device conectado:* App rodando no device *" + util.getHostName() + "*");
                }
                // Informações do sistema
                sistema = looca.getSistema();

                System.out.println("=== MONITORAMENTO DE MÁQUINA ===\n");

                // Informações da máquina
                System.out.println("HOSTNAME: " + util.getHostName());
                System.out.println("SO: " + sistema.getSistemaOperacional());

                while (true) {
                    this.loop();
                    Thread.sleep(2000);
                }
            } else {
                System.out.println("Não encontramos o device atual("+ util.getHostName() +") na nossa base de dados\n\n");
                
                System.out.println("Cadastre primeiro o device na plataforma");

//                System.out.println("Deseja cadastrar esse device no sistema?\n");
//                String res = leitor.nextLine();
//                if (res.equalsIgnoreCase("sim")) {
//                    if(computadoresRepository.store(util.getHostName(), this.fk_empresa)){
//                        System.out.println("Device cadastrado com sucesso!");
//                        this.init();
//                    }
//                } else {
//                    System.out.println("Saindo do programa...\n");
//                }
            }

        } catch (Exception e) {
            System.out.println("Erro : " + e.getMessage());
            System.out.println("Nova tentativa em 2s...");
            Thread.sleep(2000);
            this.init();
        } 
    }

    public void loop() throws InterruptedException, Exception {

        grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Volume> volumes = grupoDeDiscos.getVolumes();

        processador = looca.getProcessador();

        System.out.println("\n ==== MEMÓRIA ====");

        memoria = looca.getMemoria();
        System.out.println(memoria);

        Double memoriaDisponivel = Double.valueOf((memoria.getDisponivel() / 1024) / 1024);
        Double usoCpu = UtilApp.doubleMinimal(processador.getUso(), 4);
        Double discoDisponivel = Double.valueOf((util.getTamanhoVolumesDisponivel(volumes) / 1024) / 1024);
        
        System.out.println("\n ==== ARMAZENAMENTO ====");
        
        System.out.println("DISPONÍVEL: " + discoDisponivel);
        
        System.out.println("\n ==== PROCESSADOR ====");

        System.out.println("USO: " + usoCpu + "%\n");

        try {
            logUseCase.store(
                    fkComputador,
                    memoriaDisponivel,
                    discoDisponivel,
                    0.0,
                    usoCpu,
                    0.0,
                    50.2
            );
        } catch (Exception e) {
            System.out.println("Erro ao inserir log no banco de dados...");
        }
        
//        Integer logLastId = logUseCase.getLastInsertByComputer(fkComputador);
        
//        System.out.println("ID: "+ logLastId);

        if (memoriaDisponivel < 300 && memoriaDisponivel > 50) {
            if (quantidadeAlertasMemoria == 3) {
                slack.warningMessage(":computer: *" + util.getHostName() + ":* Memória em nível baixo");
            }
            quantidadeAlertasMemoria++;
        } else if (memoriaDisponivel < 50) {
            if (quantidadeAlertasMemoria == 5) {
                slack.errorMessage(":computer: Memória em nível crítico");
            }
            quantidadeAlertasMemoria++;
        } else {
            quantidadeAlertasMemoria = 0;
        }

        if (usoCpu >= 95) {
            if (quantidadeAlertasCPU == 3) {
                slack.warningMessage(":computer: *" + util.getHostName() + ":* Uso de cpu elevado");
            }
            quantidadeAlertasCPU++;
        } else if (usoCpu >= 99) {
            if (quantidadeAlertasCPU == 5) {
                slack.errorMessage(":computer: Uso de cpu em nível crítico");
            }
            quantidadeAlertasCPU++;
        } else {
            quantidadeAlertasCPU = 0;
        }

        if (discoDisponivel > 1024) {
            if (quantidadeAlertasDisco == 1) {
                slack.warningMessage(":computer: *" + util.getHostName() + ":* pouco espaço em disco disponível");
            }
            quantidadeAlertasDisco++;
        } else if (discoDisponivel == 200) {
            if (quantidadeAlertasDisco == 1) {
                slack.errorMessage(":computer: *" + util.getHostName() + ":* espaço em disco disponível em nível crítico");
            }
            quantidadeAlertasDisco++;
        } else {
            quantidadeAlertasDisco = 0;
        }

        System.out.println("\n\n============================");

    }

}
