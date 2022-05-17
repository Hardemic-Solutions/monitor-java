/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app;

public class UtilApp {
    public static Double longToDouble(long valor, int digitos) {
        digitos = Math.max(1, digitos);
        long positivo = Math.abs(valor);
        String texto = String.valueOf(positivo);
        if(digitos > texto.length()) {
            return (double)valor;
        }
        return Double.parseDouble(texto.substring(0, digitos)) * Long.signum(valor);
    }
    
    public static Double doubleMinimal(double valor, int digitos) {
        digitos = Math.max(1, digitos);
        double positivo = Math.abs(valor);
        String texto = String.valueOf(positivo);
        if(digitos > texto.length()) {
            return valor;
        }
        return Double.parseDouble(texto.substring(0, digitos));
    }
    
    public static String normalizarString(String str, Integer tamanhoDesejado){
        if(str.length() != tamanhoDesejado){
            Integer diferencaDesejada = str.length() - tamanhoDesejado;
            return  str + "0".repeat(diferencaDesejada);
        }
        return str;
    }
}
