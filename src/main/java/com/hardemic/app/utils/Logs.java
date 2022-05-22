package com.hardemic.app.utils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.britooo.looca.api.core.Looca;

public class Logs {

    private String nome_arquivo;
    private LocalDateTime dataAtual;
    private File arquivo;
    private File diretorio;
    private Looca locca = new Looca();
    private String rootDir;
    private String SO;

    public Logs() {

    }

    public void info(String texto) {
        gerar_Log("INFO", texto);
    }

    public void warning(String texto) {
        gerar_Log("WARNING", texto);
    }

    public void severe(String texto) {
        gerar_Log("SEVERE", texto);
    }

    private void gerar_Log(String nivel, String texto) {
        try {
            this.SO = locca.getSistema().getSistemaOperacional();
            
            if (SO.contains("Windows")){
              this.rootDir = "C:\\";
            }else{
                this.rootDir = "/home/lukas/";;
            }
            
            diretorio = new File(rootDir + "hardemic_logs/");
            
            if (!diretorio.exists()) {
                diretorio.mkdir();
            }
            dataAtual = LocalDateTime.now();
            this.nome_arquivo = "hardemic_"  + dataAtual.getDayOfMonth() + "_" + dataAtual.getMonthValue() + "_" + dataAtual.getYear();
            arquivo = new File(diretorio + nome_arquivo + ".txt");

            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            if (arquivo.length() == 0) {
                bw.write("---------- Logs hardemic ----------");
                bw.newLine();
            }

            bw.newLine();
            bw.write("[" + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "]["+ nivel +"]: " + texto);

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
