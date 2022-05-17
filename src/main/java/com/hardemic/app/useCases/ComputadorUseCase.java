/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.useCases;

import java.util.List;
import com.hardemic.app.entities.Computador;
import com.hardemic.app.repositories.ComputadoresRepository;


public class ComputadorUseCase {
    public List<Computador> index(Integer fk_empresa){
        ComputadoresRepository computadorRepository = new ComputadoresRepository();
        
        return computadorRepository.index(fk_empresa);
    }
    
    public boolean store(String hostname, Integer fk_empresa){
        ComputadoresRepository computadorRepository = new ComputadoresRepository();
        
        return computadorRepository.store(hostname, fk_empresa);
    }
    
    public List<Computador> findById(Integer id){
        ComputadoresRepository computadorRepository = new ComputadoresRepository();
        
        return computadorRepository.findById(id);
    }
    
    public List<Computador> findByHostname(String hostname){
        ComputadoresRepository computadorRepository = new ComputadoresRepository();
        
        return computadorRepository.findByHostname(hostname);
    }
}
