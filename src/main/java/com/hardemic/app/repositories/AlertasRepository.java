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
    public boolean store(Integer fk_log, String nome_alerta){
       try{
            JdbcTemplate template = new Connection().createConnection();
            template.update("INSERT INTO * tb_alerta (fk_log, nome_alerta) VALUES (?, ?)", fk_log, nome_alerta);
            logs.info("Alerta inserido no sistema");
            return true;
        }catch(DataAccessException e){
            System.out.println("Erro SQL (inserir device no bd): " + e.getMessage());
            logs.warning("Erro ao inserir device no banco de dados");
            return false;
        }catch(Exception e){
            System.out.println("Erro desconhecido (inserir device no bd): " + e.getMessage());
            return false;
        }
    }    
}
