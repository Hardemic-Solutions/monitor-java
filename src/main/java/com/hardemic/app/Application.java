/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app;

import com.hardemic.app.Views.Cli;
import com.hardemic.app.Views.LoginView;
import com.hardemic.app.services.SocketConnection;
import com.hardemic.app.utils.Logs;
import java.util.Arrays;

public class Application {  

    public static void main(String[] args) {
        Logs logs = new Logs();
        SocketConnection.getInstance();
        if(Arrays.stream(args).anyMatch("cli"::equals)){;
            logs.info("Aplicação iniciada via cli...");
            Cli.init();
        }else{
            logs.info("Aplicação iniciada via java swing...");
            LoginView.main(args);
        }
        
    }
}
