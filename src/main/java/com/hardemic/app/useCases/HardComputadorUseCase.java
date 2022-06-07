/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.useCases;

import com.hardemic.app.entities.HardComputador;
import com.hardemic.app.repositories.HardComputadoresRepository;
import java.util.List;

public class HardComputadorUseCase {
    public List<HardComputador> index(Integer fk_computador){
        HardComputadoresRepository hardComputadoresRepository = new HardComputadoresRepository();
        
        return hardComputadoresRepository.index(fk_computador);
    }
    
    public void store(Double ram, Double armazenamento, Integer gpu, Integer fk_computador, String SO){
        HardComputadoresRepository hardComputadoresRepository = new HardComputadoresRepository();
        
        hardComputadoresRepository.store(ram, armazenamento, gpu, fk_computador, SO);
    }
    
    public List<HardComputador> findById(Integer id){
        HardComputadoresRepository hardComputadoresRepository = new HardComputadoresRepository();
        
        return hardComputadoresRepository.findById(id);
    }
    
    public List<HardComputador> findByFkComputador(Integer fk_computador){
        HardComputadoresRepository hardComputadoresRepository = new HardComputadoresRepository();
        
        return hardComputadoresRepository.findByFkComputador(fk_computador);
    }
}
