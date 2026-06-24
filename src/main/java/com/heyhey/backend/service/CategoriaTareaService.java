package com.heyhey.backend.service;

import com.heyhey.backend.model.CategoriaTarea;
import com.heyhey.backend.repository.CategoriaTareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // clase que contiene la logica del negocio
public class CategoriaTareaService {

    @Autowired //llama a la intefaz para poder usar funciones de sql
    private CategoriaTareaRepository categoriaTareaRepository;

    //Metodo para crear o actualizar categoria
    public CategoriaTarea guardarCategoria(CategoriaTarea categoria){
        // save() hace un insert si no existe, o un update si ya existe el ID
        return categoriaTareaRepository.save(categoria);
    }

    //Metodo para leer todas las categorias (SELECT *)
    public List<CategoriaTarea> ObtenerCategorias(){
        return categoriaTareaRepository.findAll();
    }

    //Metodo para leer una sola categoria
    public CategoriaTarea obtenerId(Integer id){
        // orElse es si no la encuentras, devuelve un valor nulo
        return categoriaTareaRepository.findById(id).orElse(null);
    }

    //Metodo para Borrar una categoria
    public void eliminarCategoria(Integer id){
        categoriaTareaRepository.deleteById(id);
    }
}
