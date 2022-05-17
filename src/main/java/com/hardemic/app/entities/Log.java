/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hardemic.app.entities;

import java.util.Date;

public class Log {

    private Integer idLog;
    private Integer fkComputador;
    private Double ram;
    private Double disco;
    private Double gpu;
    private Double cpu;
    private Double rede;
    private Double temperatura;
    private String dataLog;

    public Log() {
    }

    public Log(Double ram, Double disco, Double gpu, Double cpu, Double rede) {
        this.idLog = null;
        this.ram = ram;
        this.disco = disco;
        this.gpu = gpu;
        this.cpu = cpu;
        this.rede = rede;
        this.temperatura = 0.0;
        this.dataLog = new Date().toString();
    }

    public Log(Double ram, Double disco, Double gpu, Double cpu, Double rede, Double temperatura) {
        this.idLog = null;
        this.ram = ram;
        this.disco = disco;
        this.gpu = gpu;
        this.cpu = cpu;
        this.rede = rede;
        this.temperatura = temperatura;
        this.dataLog = new Date().toString();
    }

    public Log(Integer id_log, Double ram, Double disco, Double gpu, Double cpu, Double rede, Double temperatura, String data_log) {
        this.idLog = id_log;
        this.ram = ram;
        this.disco = disco;
        this.gpu = gpu;
        this.cpu = cpu;
        this.rede = rede;
        this.temperatura = temperatura;
        this.dataLog = data_log;
    }

    public Integer getId_log() {
        return idLog;
    }

    public void setId_log(Integer id_log) {
        this.idLog = id_log;
    }

    public Integer getFkComputador() {
        return fkComputador;
    }

    public void setFkComputador(Integer fk_computador) {
        this.fkComputador = fk_computador;
    }

    public Double getRam() {
        return ram;
    }

    public void setRam(Double ram) {
        this.ram = ram;
    }

    public Double getDisco() {
        return disco;
    }

    public void setDisco(Double disco) {
        this.disco = disco;
    }

    public Double getGpu() {
        return gpu;
    }

    public void setGpu(Double gpu) {
        this.gpu = gpu;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public Double getRede() {
        return rede;
    }

    public void setRede(Double rede) {
        this.rede = rede;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public String getData_log() {
        return dataLog;
    }

    public void setData_log(String data_log) {
        this.dataLog = data_log;
    }

    @Override
    public String toString() {
        return "Log{" + "id_log=" + idLog + ", fk_computador=" + fkComputador + ", ram=" + ram + ", disco=" + disco + ", gpu=" + gpu + ", cpu=" + cpu + ", rede=" + rede + ", temperatura=" + temperatura + ", data_log=" + dataLog + '}';
    }
    
    

}
