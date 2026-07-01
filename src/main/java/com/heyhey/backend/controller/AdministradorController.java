package com.heyhey.backend.controller;

import com.heyhey.backend.model.Administrador;
import com.heyhey.backend.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public List<Administrador> gettAll(){
        return administradorService.obtenerTodos();
    }

    @PostMapping
    public Administrador postAdministrador(@RequestBody Administrador administrador){
        return administradorService.guardarAdministrador(administrador);
    }

}
