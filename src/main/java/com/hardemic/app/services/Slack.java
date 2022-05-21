/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import org.json.JSONObject;

import com.hardemic.app.utils.Logs;

public class Slack {
    private String url;
    private Logs logs = new Logs();
     
    public Slack() {
        try {
           InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
           Properties prop = new Properties();

           // load a properties file
           prop.load(input);
           
           this.url = prop.getProperty("slack.url");

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de configurações não encontrado...");
            logs.warning("Arquivo de configurações não encontrado");
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivos de configurações...");
            logs.warning("Erro ao ler arquivo de configurações");
        } 
    }
     
     
     
     private void sendMessage(JSONObject message){
        try{
            URL obj = new URL(this.url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(message.toString());

            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

//            System.out.println("Sending 'POST' request to URL: " + this.url);
//            System.out.println("POST parameters: " + message.toString());
//            System.out.println("Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            reader.close();
            
        }catch(Exception e){
            System.out.println("POST slack api failed");
            logs.warning("Erro ao enviar alerta para o slack");
        }
    }
     
     public void infoMessage(String message) throws Exception{
        JSONObject json = new JSONObject(); 
         
        json.put("text", " :information_source: *INFO* \n\n \t" + message);
        this.sendMessage(json);
//        System.out.println("Mensagem de info enviada com sucesso");
     }
     
     public void warningMessage(String message) throws Exception{
        JSONObject json = new JSONObject(); 
         
        json.put("text", " :warning: *ALERT* \n\n \t" + message);
        this.sendMessage(json);
//        System.out.println("Mensagem de atenção enviada com sucesso");
     }
     
     public void errorMessage(String message) throws Exception{
        JSONObject json = new JSONObject(); 
         
        json.put("text", " :exclamation: *URGENT* \n\n \t" + message);
        this.sendMessage(json);
//        System.out.println("Mensagem de erro enviada com sucesso");
     }
     
     public void personalMessage(String message) throws Exception {
        JSONObject json = new JSONObject(); 
         
        json.put("text", message);
        this.sendMessage(json);
//        System.out.println("Mensagem personalizada enviada com sucesso");
     }
}
