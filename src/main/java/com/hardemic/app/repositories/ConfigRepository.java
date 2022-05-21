/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.repositories;

import com.hardemic.database.Connection;
import com.hardemic.app.entities.Config;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConfigRepository {
     public List<Config> index(Integer fk_empresa) {
        try {
            JdbcTemplate template = new Connection().createConnection();
            return template.query("SELECT * FROM tb_configs where fk_empresa = ?", new BeanPropertyRowMapper<>(Config.class), fk_empresa);
        } catch (DataAccessException e) {
            System.out.println("Erro SQL (listar configs da empresa no bd): " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Erro desconhecido (listar configs da empresa no bd): " + e.getMessage());
            return null;
        }
    }

    public void update(Integer valor, Boolean situacao, Integer id_config) {
        JdbcTemplate template = new Connection().createConnection();
        template.update("UPDATE FROM tb_configs SET valor = ?, situacao = ? where id_config = ?", valor, situacao, id_config);
    }
}
