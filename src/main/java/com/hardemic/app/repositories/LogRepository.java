/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hardemic.app.repositories;

import com.hardemic.app.entities.Computador;
import com.hardemic.database.Connection;
import com.hardemic.app.entities.Log;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LogRepository {

    public boolean store(Integer fk_computador, Double memoriaDisponivel, Double discoDisponivel, Double usoGpu, Double usoCpu, Double usoRede, Double temperatura) {
        try {
            JdbcTemplate template = new Connection().createConnection();
            
            String sql = "INSERT INTO tb_logs("
                    + "fk_computador,"
                    + "ram,"
                    + "disco,"
                    + "gpu,"
                    + "cpu,"
                    + "rede,"
                    + "temperatura) "
                    + "VALUES(?,?,?,?,?,?,?)";
            
            template.update(sql,
                    fk_computador,
                    memoriaDisponivel,
                    discoDisponivel,
                    usoGpu,
                    usoCpu,
                    usoRede,
                    temperatura
            );
            
            System.out.println("Inserindo dados no BD...");

            return true;
        } catch (DataAccessException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public List<Log> getLastInsertByComputerId(Integer fk_computador){
        
        JdbcTemplate template = new Connection().createConnection();
        
        return template.query("SELECT Max(id_log) FROM tb_logs where fk_computador = ?;", new BeanPropertyRowMapper<>(Log.class), fk_computador);
    }
    
    public Integer getLastInsertByComputer(Integer fk_computador){
        
        try{
            JdbcTemplate template = new Connection().createConnection();
        
            SqlRowSet rows = template.queryForRowSet("SELECT Max(id_log) FROM tb_logs where fk_computador = "+ fk_computador +";");
            
            Integer id_log = 0;
            
            while ( rows.next()  ){
                id_log = rows.getInt("id_log");
                System.out.println("id_log:: " + id_log);
            }
            
            return id_log;

        }catch(DataAccessException e){
            System.out.println("Erro: " + e.getMessage());
        }
        
        return 0;
    }
}
