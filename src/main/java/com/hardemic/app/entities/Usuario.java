/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.entities;

public class Usuario {
    private Integer idUsuario;
    private String nomeUsuario;
    private String permissao;
    private Integer fkEmpresa;
    private String senha;
    
    public Usuario(){
    
    }

    public Usuario(Integer id_usuario, String nome_usuario, String permissao, String senha, Integer fk_empresa) {
        this.idUsuario = id_usuario;
        this.nomeUsuario = nome_usuario;
        this.permissao = permissao;
        this.senha = senha;
        this.fkEmpresa = fk_empresa;
    }

    public Integer getId_usuario() {
        return idUsuario;
    }

    public String getNome_usuario() {
        return nomeUsuario;
    }

    public String getPermissao() {
        return permissao;
    }
    
    public Integer getFk_empresa() {
        return fkEmpresa;
    }

    public String getSenha() {
        return senha;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }  

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nomeUsuario=" + nomeUsuario + ", permissao=" + permissao + ", fkEmpresa=" + fkEmpresa + '}';
    }
    
}
