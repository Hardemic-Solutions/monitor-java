/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app;

import com.hardemic.app.Views.Cli;
import com.hardemic.app.Views.LoginView;
import java.util.Arrays;

public class Application {  

    public static void main(String[] args) {
       
        if(Arrays.stream(args).anyMatch("cli"::equals)){
            Cli.init();
        }else{
            LoginView.main(args);
        }
        
    }
}
