/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hardemic.app.repositories;

import com.hardemic.app.utils.Logs;
import com.hardemic.database.Connection;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class AlertasRepository {

    private Logs logs = new Logs();

    public boolean store(Long fk_log, String nome_alerta) {
        try {
            String query = "INSERT INTO tb_alerta (fk_log, nome_alerta) VALUES (?, ?)";
            System.out.println("fk_log " + fk_log);
            JdbcTemplate template = new Connection().createConnection();
            template.update(query, fk_log, nome_alerta);
            return true;
        } catch (DataAccessException e) {
            System.out.println("Erro ao inserir alerta: " + e.getMessage());
            logs.warning("Erro ao inserir alerta: " + e.getMessage());
            return false;
        }
    }
}
