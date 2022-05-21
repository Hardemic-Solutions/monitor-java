/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.entities;

public class Computador {
    private Integer id_computador;
    private String hostname;;
    private String patrimonio;
    private Integer fk_empresa;
    private Integer fk_sala;

    public Computador() {
        
    }

    public Integer getId_Computador() {
        return id_computador;
    }

    public void setId_Computador(Integer id_computador) {
        this.id_computador = id_computador;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Integer getFk_empresa() {
        return fk_empresa;
    }

    public void setFk_empresa(Integer fk_empresa) {
        this.fk_empresa = fk_empresa;
    }

    public Integer getFk_sala() {
        return fk_sala;
    }

    public void setFk_sala(Integer fk_sala) {
        this.fk_sala = fk_sala;
    }

    @Override
    public String toString() {
        return "Computador{" + "id=" + id_computador + ", hostname=" + hostname + ", patrimonio=" + patrimonio + ", fk_empresa=" + fk_empresa + ", fk_sala=" + fk_sala + '}';
    }
    
    
    
}
