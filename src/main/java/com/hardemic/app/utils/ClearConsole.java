/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.utils;

public class ClearConsole {
    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");
//            System.out.println("OS: " + os);
            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls").waitFor();
            }
            else
            {
                Runtime.getRuntime().exec("clear").waitFor();
                System.out.print("\033\143");
            }
        }
        catch (final Exception e)
        {
            System.out.println("e: " + e.getMessage());
        }
    }
}
