/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.repositories;

import com.hardemic.app.entities.Usuario;
import com.hardemic.database.Connection;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UsuarioRepository {
    public List<Usuario> index(){
        JdbcTemplate template = new Connection().createConnection();
        return template.query("SELECT * FROM tb_usuario", new BeanPropertyRowMapper<>(Usuario.class));
    }
    
    public List<Usuario> show(Integer id){
        JdbcTemplate template = new Connection().createConnection();
        return template.query("SELECT * FROM tb_usuario where id = ?", new BeanPropertyRowMapper<>(Usuario.class), new Object[]{id});
    }
    
    public List<Usuario> authenticate(String email, String password){
        JdbcTemplate template = new Connection().createConnection();
        return template.query("SELECT * FROM tb_usuario where email = ? and senha = ?", new BeanPropertyRowMapper<>(Usuario.class), new Object[]{  email, password });
    }
}
