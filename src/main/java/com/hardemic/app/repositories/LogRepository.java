/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hardemic.app.repositories;

import com.hardemic.database.Connection;
import com.hardemic.app.entities.Log;
import com.hardemic.app.utils.Logs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LogRepository {
    private final Logs logs = new Logs();

    public long store(Integer fk_computador, Double memoriaDisponivel, Double discoDisponivel, Double usoGpu, Double usoCpu, Double usoRede, Double temperatura) {
        String sql = "INSERT INTO tb_logs("
                    + "fk_computador,"
                    + "ram,"
                    + "disco,"
                    + "gpu,"
                    + "cpu,"
                    + "rede,"
                    + "temperatura) "
                    + "VALUES(?,?,?,?,?,?,?)";

         JdbcTemplate template = new Connection().createConnectionMysql();
         template.update(sql, fk_computador, memoriaDisponivel,discoDisponivel,usoGpu,usoCpu,usoRede,temperatura);
        // try{
        //     JdbcTemplate template = new Connection().createConnectionMysql();
        //     template.update(sql, fk_computador, memoriaDisponivel,discoDisponivel,usoGpu,usoCpu,usoRede,temperatura);
        //     System.out.println("Inserido no bd local com sucesso!");
        // }catch(DataAccessException e){
        //     System.out.println("Erro SQL (inserir device no bd local): " + e.getMessage());
        // }catch(Exception e){
        //     System.out.println("Erro desconhecido (inserir device no bd local): " + e.getMessage());
        // }
                    
        try (PreparedStatement statement = new Connection().getDataSource().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, fk_computador);
            statement.setDouble(2, memoriaDisponivel);
            statement.setDouble(3, discoDisponivel);
            statement.setDouble(4, usoGpu);
            statement.setDouble(5, usoCpu);
            statement.setDouble(6, usoRede);
            statement.setDouble(7, temperatura);
            int affectedRows = statement.executeUpdate();
            
            try (ResultSet keys = statement.getGeneratedKeys()) {
                while(keys.next()){
                 return keys.getLong(1);
                }
            }
            // omitted
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            logs.warning("Não foi possível inserir no bd o log do sistema");
            return 0;
        }
        return 0;
    }

    public List<Log> getLastInsertByComputerId(Integer fk_computador) {

        JdbcTemplate template = new Connection().createConnection();

        return template.query("SELECT Max(id_log) FROM tb_logs where fk_computador = ?;", new BeanPropertyRowMapper<>(Log.class
        ), fk_computador);
    }

    public Integer getLastInsertByComputer(Integer fk_computador) {

        try {
            JdbcTemplate template = new Connection().createConnection();

            SqlRowSet rows = template.queryForRowSet("SELECT Max(id_log) FROM tb_logs where fk_computador = " + fk_computador + ";");

            Integer id_log = 0;

            while (rows.next()) {
                id_log = rows.getInt("id_log");
                System.out.println("id_log:: " + id_log);
            }

            return id_log;

        } catch (DataAccessException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return 0;
    }
}
