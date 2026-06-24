package com.heyhey.backend.service;

import com.heyhey.backend.model.Persona;
import com.heyhey.backend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public Persona guardarPersona(Persona persona){
        return personaRepository.save(persona);
    }

    public List<Persona> obtenerPersonas(){
        return personaRepository.findAll();
    }

    public Persona obtenerId(Integer id){
        return personaRepository.findById(id).orElse(null);
    }

    public void eliminarPersona(Integer id){
        personaRepository.deleteById(id);
    }
}

