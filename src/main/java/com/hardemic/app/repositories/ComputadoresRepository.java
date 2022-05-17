/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.repositories;

import com.hardemic.database.Connection;
import com.hardemic.app.entities.Computador;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ComputadoresRepository {
   
    public List<Computador> index(Integer fk_empresa){
        JdbcTemplate template = new Connection().createConnection();
        return template.query("SELECT * FROM tb_computadores where fk_empresa = ?", new BeanPropertyRowMapper<>(Computador.class), fk_empresa);
    }
    
    public boolean store(String hostname, Integer fk_empresa){
        try{
            JdbcTemplate template = new Connection().createConnection();
            template.update("INSERT INTO * tb_computadores (hostname, fk_empresa) VALUES (?, ?)", hostname, fk_empresa);
            return true;
        }catch(DataAccessException e){
            System.out.println("Erro SQL (inserir device no bd): " + e.getMessage());
             return false;
        }catch(Exception e){
            System.out.println("Erro desconhecido (inserir device no bd): " + e.getMessage());
            return false;
        }
    }
    
    public List<Computador> findById(Integer id){
        JdbcTemplate template = new Connection().createConnection();
        return template.query("SELECT * FROM tb_computadores where id = ?", new BeanPropertyRowMapper<>(Computador.class), id);
    }
    
    public List<Computador> findByHostname(String hostname){
        JdbcTemplate template = new Connection().createConnection();
        return template.query("SELECT * FROM tb_computadores where hostname = ?", new BeanPropertyRowMapper<>(Computador.class), hostname);
    }
}
