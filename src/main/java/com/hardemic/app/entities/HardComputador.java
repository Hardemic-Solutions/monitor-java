/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.entities;

public class HardComputador {
    private Integer id_hard;
    private Double ram;
    private Double armazenamento;
    private Integer gpu; 
    private Integer fk_computador; 
    private String dataHardComputador; 
    private String SO; 

    public HardComputador() {
    }
    
    

    public Integer getId_hard() {
        return id_hard;
    }

    public void setId_hard(Integer id_hard) {
        this.id_hard = id_hard;
    }

    public Double getRam() {
        return ram;
    }

    public void setRam(Double ram) {
        this.ram = ram;
    }

    public Double getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(Double armazenamento) {
        this.armazenamento = armazenamento;
    }

    public Integer getGpu() {
        return gpu;
    }

    public void setGpu(Integer gpu) {
        this.gpu = gpu;
    }

    public Integer getFk_computador() {
        return fk_computador;
    }

    public void setFk_computador(Integer fk_computador) {
        this.fk_computador = fk_computador;
    }

    public String getDataHardComputador() {
        return dataHardComputador;
    }

    public void setDataHardComputador(String dataHardComputador) {
        this.dataHardComputador = dataHardComputador;
    }

    public String getSO() {
        return SO;
    }

    public void setSO(String SO) {
        this.SO = SO;
    }

    @Override
    public String toString() {
        return "HardComputador{" + "id_hard=" + id_hard + ", ram=" + ram + ", armazenamento=" + armazenamento + ", gpu=" + gpu + ", fk_computador=" + fk_computador + ", dataHardComputador=" + dataHardComputador + ", SO=" + SO + '}';
    }
    
    
}
