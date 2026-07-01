package com.heyhey.backend.service;

import com.heyhey.backend.model.Administrador;
import com.heyhey.backend.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;

    public Administrador guardarAdministrador (Administrador administrador){
        return administradorRepository.save(administrador);
    }

    public List<Administrador> obtenerTodos(){
        return administradorRepository.findAll();
    }

    public Administrador obtenerId(Integer id){
        return administradorRepository.findById(id).orElse(null);
    }

    public void eliminarAdministrador(Integer id){
        administradorRepository.deleteById(id);
    }
}
