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
import com.hardemic.app.entities.HardComputador;
import com.hardemic.app.services.Slack;
import com.hardemic.app.utils.ClearConsole;
import com.hardemic.app.utils.Colors;
import com.hardemic.app.utils.Logs;
import com.hardemic.app.utils.ProcessarAlerta;

import java.util.List;


public class Monitor {

    // Singletown
    private final Looca looca = new Looca();
    private final UtilLooca util = new UtilLooca();
    private final Slack slack = new Slack();
    private final Logs logs = new Logs();
    private final ProcessarAlerta processarAlerta;

    // Repositories
    private final LogUseCase logUseCase = new LogUseCase();
    private final ComputadorUseCase computadorUseCase = new ComputadorUseCase();
    private final HardComputadorUseCase hardComputadorUseCase = new HardComputadorUseCase();
 

    // Grupos looca
    private Sistema sistema;
    private Memoria memoria;
    private Processador processador = getLooca().getProcessador();
    private DiscosGroup grupoDeDiscos;

    // Dados do usuário logado
    private Integer fkComputador;

    // Contador para verificar se o sistema já foi iniciado
    private Integer inicializado = 0;
    
    // Contador para registro no banco
    private Integer segundos = 0;

    public Monitor(Integer fkEmpresa) {
        processarAlerta = new ProcessarAlerta(fkEmpresa);
    }

    public Looca getLooca() {
        return looca;
    }

    public void init() {
        try {
            ClearConsole.clearConsole();
            sistema = looca.getSistema();
            inicializado++;
            List<Computador> computadores = computadorUseCase.findByHostname(util.getHostName());

            if (!computadores.isEmpty()) {
                this.fkComputador = computadores.get(0).getId_Computador();
                grupoDeDiscos = looca.getGrupoDeDiscos();
                memoria = looca.getMemoria();

                List<HardComputador> hardComputadores = hardComputadorUseCase.findByFkComputador(this.fkComputador);

                if (hardComputadores.isEmpty()) {

                    hardComputadorUseCase.store(
                            Double.valueOf((memoria.getTotal() / 1024) / 1024),
                            Double.valueOf((grupoDeDiscos.getTamanhoTotal() / 1024) / 1024),
                            0.0,
                            this.fkComputador,
                            sistema.getSistemaOperacional()
                    );

                    System.out.println("Novo hardware de pc identificado!");
                    slack.infoMessage(":computer: *Novo hardware de pc identificado*: *" + util.getHostName() + "*");;
                } else {
                    // Implementar a lógica para verficar se o hardware é o mesmo
                    HardComputador hardPc = hardComputadores.get(0);
                    boolean mudou = false;

                    if (hardPc.getRam() != Double.valueOf((memoria.getTotal() / 1024) / 1024)) {
                         mudou = true;
                        hardComputadorUseCase.store(
                            Double.valueOf((memoria.getTotal() / 1024) / 1024),
                            Double.valueOf((grupoDeDiscos.getTamanhoTotal() / 1024) / 1024),
                            0.0,
                            this.fkComputador,
                            sistema.getSistemaOperacional()
                        );
                    } else if (hardPc.getArmazenamento() != (grupoDeDiscos.getTamanhoTotal() / 1024) / 1024) {
                         mudou = true;
                        hardComputadorUseCase.store(
                            Double.valueOf((memoria.getTotal() / 1024) / 1024),
                            Double.valueOf((grupoDeDiscos.getTamanhoTotal() / 1024) / 1024),
                            0.0,
                            this.fkComputador,
                            sistema.getSistemaOperacional()
                    );
                    } else if (hardPc.getGpu() != 0) {
                         mudou = true;
                        hardComputadorUseCase.store(
                            Double.valueOf((memoria.getTotal() / 1024) / 1024),
                            Double.valueOf((grupoDeDiscos.getTamanhoTotal() / 1024) / 1024),
                            0.0,
                            this.fkComputador,
                            sistema.getSistemaOperacional()
                    );

                    } else if (hardPc.getSO() != sistema.getSistemaOperacional()) {
                         mudou = true;
                        hardComputadorUseCase.store(
                            Double.valueOf((memoria.getTotal() / 1024) / 1024),
                            Double.valueOf((grupoDeDiscos.getTamanhoTotal() / 1024) / 1024),
                            0.0,
                            this.fkComputador,
                            sistema.getSistemaOperacional()
                    );

                    if(mudou == true)   
                        System.out.println("Hardware atualizado no sistema");
                        logs.info("Hardware atualizado no sistema");
                    }
                }

                if (inicializado == 1) {
                    slack.infoMessage(":computer: *Device conectado:* App rodando no device *" + util.getHostName() + "*");
                }

                while (true) {
                    Thread.sleep(1000);
                    segundos++;
                    ClearConsole.clearConsole();
                    this.loop();
                }
            } else {
                System.out.println("Não encontramos o device atual(" + util.getHostName() + ") na nossa base de dados\n\n");
                System.out.println("Cadastre primeiro o device na plataforma");
            }

        } catch (Exception e) {
            System.out.println("Erro : " + e.getMessage());
            System.out.println("Nova tentativa em 1s...");
            try {
                Thread.sleep(1000);
                this.init();
            } catch (InterruptedException ex) {
               logs.severe("Erro ao reiniciar a aplicação, erro: " + ex.getMessage() );
            }
        }
    }

    private void loop() throws InterruptedException, Exception {
        grupoDeDiscos = looca.getGrupoDeDiscos();
        memoria = looca.getMemoria();

        List<Volume> volumes = grupoDeDiscos.getVolumes();
        Double memoriaDisponivel = Double.valueOf((memoria.getDisponivel() / 1024) / 1024);
        Double usoCpu = processador != null ? UtilApp.doubleMinimal(processador.getUso(), 4) : 0.0;
        Double discoDisponivel = Double.valueOf((util.getTamanhoVolumesDisponivel(volumes) / 1024) / 1024);

        System.out.println(Colors.YELLOW);
        // Informações do sistema
        System.out.println("====================   INFORMAÇÕES DA MÁQUINA   ======================");
        System.out.println("                                                                    ");
        System.out.println(" HOSTNAME: " + util.getHostName());
        System.out.println(" SO: " + sistema.getSistemaOperacional());
        System.out.println("                                                                    ");
        System.out.println("====================   INFORMAÇÕES DO HARDWARE  ======================");
        System.out.println("                                                                     ");
        System.out.println(" DISCO: " + ((grupoDeDiscos.getTamanhoTotal() / 1024) / 1024 / 1024) + "GB");
        System.out.println(" RAM: " + ((memoria.getTotal() / 1024) / 1024 / 1024) + "GB");
        System.out.println(" GPU: N/A                                                            ");
        System.out.println("                                                                     ");
        System.out.println("======================================================================\n");

        System.out.println(
                String.format(
                        " MEMÓRIA:             %.2fGB usado / %dGB total \n"
                        + " ARMAZENAMENTO:       %dGB usado / %dGB total\n"
                        + " USO PROCESSADOR:     %s%%"
                        + "\n\n",
                        memoriaDisponivel,
                        (memoria.getTotal() / 1024) / 1024 / 1024,
                        discoDisponivel.intValue(),
                        ((grupoDeDiscos.getTamanhoTotal() / 1024) / 1024 / 1024),
                        usoCpu
                )
        );

        System.out.println("======================================================================\n");
        if(segundos == 20){
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
               logs.severe("Erro ao inserir logs no banco");
           }
           segundos = 0;
        }
       
        
        if(processarAlerta.init(memoriaDisponivel, discoDisponivel, usoCpu)){
            segundos = 0;
        }
        processador = looca.getProcessador();

    }

}
