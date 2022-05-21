/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.entities;

public class Config {
    private Integer id_config;
    private String nome_config;
    private Integer valor;
    private Integer fk_empresa;
    private Boolean situacao;

    public Config() {
    }

    public Integer getId_config() {
        return id_config;
    }

    public void setId_config(Integer id_config) {
        this.id_config = id_config;
    }

    public String getNome_config() {
        return nome_config;
    }

    public void setNome_config(String nome_config) {
        this.nome_config = nome_config;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getFk_empresa() {
        return fk_empresa;
    }

    public void setFk_empresa(Integer fk_empresa) {
        this.fk_empresa = fk_empresa;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Config{" + "id_config=" + id_config + ", nome_config=" + nome_config + ", valor=" + valor + ", fk_empresa=" + fk_empresa + ", situacao=" + situacao + '}';
    }
    
    
}
