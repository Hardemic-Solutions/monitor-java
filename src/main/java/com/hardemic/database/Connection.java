/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hardemic.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Connection {

    private BasicDataSource dataSource;
    private BasicDataSource dataSourceMySql;


    public Connection() {
        InputStream input;

        try {
            input = getClass().getClassLoader().getResourceAsStream("config.properties");
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dataSource.setUrl(prop.getProperty("db.url"));
            
            dataSourceMySql = new BasicDataSource();
            dataSourceMySql.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSourceMySql.setUrl("jdbc:mysql://database:3306/hardemic");
            dataSourceMySql.setUsername("root");
            dataSourceMySql.setPassword("root");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de configurações não encontrado...");
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivos de configurações...");
        } 
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
    
    public BasicDataSource getDataSourceMySql() {
        return dataSourceMySql;
    }

    public JdbcTemplate createConnection() {
        return new JdbcTemplate(dataSource);
    }
    
    public JdbcTemplate createConnectionMysql() {
        return new JdbcTemplate(dataSourceMySql);
    }
}