/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.useCases;

import com.hardemic.app.repositories.AlertasRepository;

public class AlertasUseCase {
    public boolean store(Integer fk_log, String nome_alerta){
        AlertasRepository alertasRepository = new AlertasRepository();
        return alertasRepository.store(fk_log,nome_alerta);
    }
}
