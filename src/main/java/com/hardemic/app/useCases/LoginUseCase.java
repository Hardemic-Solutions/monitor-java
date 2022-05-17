/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.useCases;

import com.hardemic.app.entities.Usuario;
import com.hardemic.app.repositories.UsuarioRepository;
import java.util.List;

public class LoginUseCase {
    public List<Usuario> authenticate(String email, String password){
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        
        return usuarioRepository.authenticate(email, password);
    }
}
