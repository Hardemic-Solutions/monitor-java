/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.useCases;

import com.hardemic.app.repositories.LogRepository;
import java.util.List;
import com.hardemic.app.entities.Log;

public class LogUseCase {
    public boolean store(Integer fk_computador, Double memoriaDisponivel, Double discoDisponivel, Double usoGpu, Double usoCpu, Double usoRede, Double temperatura){
        
        LogRepository logRepository = new LogRepository();
        
        return logRepository.store(fk_computador, memoriaDisponivel, discoDisponivel, usoGpu, usoCpu, usoRede, temperatura);
        
    }
    
    public List<Log> getLastInsertByComputerId(Integer fk_computador){
        LogRepository logRepository = new LogRepository();
        return logRepository.getLastInsertByComputerId(fk_computador);
    }
    
    public Integer getLastInsertByComputer(Integer fk_computador){
        LogRepository logRepository = new LogRepository();
        return logRepository.getLastInsertByComputer(fk_computador);
    }
    
}
