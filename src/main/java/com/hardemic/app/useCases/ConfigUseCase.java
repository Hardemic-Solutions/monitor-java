/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.useCases;

import com.hardemic.app.entities.Config;
import com.hardemic.app.repositories.ConfigRepository;
import java.util.List;

public class ConfigUseCase {
    public List<Config> index(Integer fk_empresa) {
        ConfigRepository configRepository = new ConfigRepository();
        
        return configRepository.index(fk_empresa);
    }
}
