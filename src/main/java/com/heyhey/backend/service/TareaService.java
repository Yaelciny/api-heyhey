package com.heyhey.backend.service;

import com.heyhey.backend.model.Tarea;
import com.heyhey.backend.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    public Tarea gurdarTarea(Tarea tarea){
        return tareaRepository.save(tarea);
    }

    public List<Tarea> obtenerTareas(){
        return tareaRepository.findAll();
    }

    public Tarea obtenerId(Integer id){
        return tareaRepository.findById(id).orElse(null);
    }

    public void eliminarTarea(Integer id){
        tareaRepository.deleteById(id);
    }
}
