package com.heyhey.backend.controller;

import com.heyhey.backend.model.CategoriaTarea;
import com.heyhey.backend.repository.CategoriaTareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria-tarea")
public class CategoriaTareaController {

    @Autowired
    private CategoriaTareaRepository categoriaTareaRepository;

    // Obtener todas las categorías para el menú desplegable
    @GetMapping
    public List<CategoriaTarea> obtenerTodas() {
        return (List<CategoriaTarea>) categoriaTareaRepository.findAll();
    }

    // Crear una nueva categoría desde Next.js
    @PostMapping
    public CategoriaTarea crearCategoria(@RequestBody CategoriaTarea categoria) {
        return categoriaTareaRepository.save(categoria);
    }
}