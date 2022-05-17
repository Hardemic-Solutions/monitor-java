/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app;

import com.hardemic.app.useCases.Monitor;
import com.hardemic.app.useCases.LoginUseCase;

import com.hardemic.app.Views.LoginView;
import com.hardemic.app.entities.Usuario;
import com.hardemic.app.utils.Logs;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {  

    public static void main(String[] args) {
        LoginUseCase login = new LoginUseCase();
        Scanner leitor = new Scanner(System.in);
        
        Logs logs = new Logs();
        

        logs.warning("Atenção!");

        logs.warning("Atenção trlala");

       
        if(Arrays.stream(args).anyMatch("cli"::equals)){
            System.out.println("Application CLI\n");
            
            System.out.println("=== LOGIN ===\n");
            
            String email;
            
            String password;
            
            List<Usuario> usuario;
            
            do{
                System.out.println("Informe seu email: ");
            
                email = leitor.nextLine();

                System.out.println("Informe sua senha: ");

                password = leitor.nextLine();
                
                usuario = login.authenticate(email, password);
                
                if(usuario.isEmpty()){
                    System.out.println("\nCredenciais inválidas! Informe novamente seus dados...");
                }
            }while(usuario.isEmpty());
            
            System.out.println("Usuário "+ usuario.get(0).getNome_usuario() +" autenticado!\n");
            
            Monitor monitor = new Monitor(usuario.get(0).getFk_empresa());
            
            try {
                monitor.init();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }else{
            LoginView.main(args);
        }
        
    }
}
