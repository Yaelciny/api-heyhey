package com.heyhey.backend.controller;

import com.heyhey.backend.model.Persona;
import com.heyhey.backend.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> getAll(){
        return personaService.obtenerPersonas();
    }

    @PostMapping
    public Persona postPersona(@RequestBody Persona persona){
        return personaService.guardarPersona(persona);
    }

}
