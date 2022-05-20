/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hardemic.app.repositories;

import com.hardemic.database.Connection;
import com.hardemic.app.entities.HardComputador;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class HardComputadoresRepository {

    public List<HardComputador> index(Integer fk_computador) {
        try{
        JdbcTemplate template = new Connection().createConnection();
        return template.query("SELECT * FROM tb_computadores where fk_computador = ?", new BeanPropertyRowMapper<>(HardComputador.class), fk_computador);
            
        }catch (DataAccessException e) {
            System.out.println("Erro SQL (listar hardware do computador no bd): " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Erro desconhecido (listar hardware do computador no bd): " + e.getMessage());
            return null;
        }
    }

    public boolean store(Integer ram, Integer armazenamento, Integer gpu, Integer fk_computador, String SO) {
        try {
            JdbcTemplate template = new Connection().createConnection();
            template.update("INSERT INTO * tb_hard_computadores ( ram, armazenamento, gpu, fk_computador, SO ) VALUES (?, ?, ?, ?, ?)", ram, armazenamento, gpu, fk_computador, SO);
            return true;
        } catch (DataAccessException e) {
            System.out.println("Erro SQL (inserir hardware do computador no bd): " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Erro desconhecido (inserir hardware do computador no bd): " + e.getMessage());
            return false;
        }
    }

    public List<HardComputador> findById(Integer id) {
        try {
            JdbcTemplate template = new Connection().createConnection();
            return template.query("SELECT * FROM tb_hard_computadores where id = ?", new BeanPropertyRowMapper<>(HardComputador.class), id);
        } catch (DataAccessException e) {
            System.out.println("Erro SQL (inserir hardware do computador no bd): " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Erro desconhecido (inserir device no bd): " + e.getMessage());
            return null;
        }

    }

    public List<HardComputador> findByFkComputador(Integer fk_computador) {
        try {
            JdbcTemplate template = new Connection().createConnection();
            return template.query("SELECT * FROM tb_hard_computadores where fk_computador = ?", new BeanPropertyRowMapper<>(HardComputador.class), fk_computador);
        } catch (DataAccessException e) {
            System.out.println("Erro SQL (listar hardware do computador por hostname): " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Erro desconhecido (listar hardware do computador por hostname no bd): " + e.getMessage());
            return null;
        }

    }
}
