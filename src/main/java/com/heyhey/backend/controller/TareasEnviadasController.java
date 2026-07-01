package com.heyhey.backend.controller;

import com.heyhey.backend.model.TareasEnviadas;
import com.heyhey.backend.service.TareasEnviadasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas-enviadas")
public class TareasEnviadasController {
    @Autowired
    private TareasEnviadasService tareasEnviadasService;

    @GetMapping
    public List<TareasEnviadas> getAll(){
        return tareasEnviadasService.obtenerTodas();
    }

    @PostMapping
    public TareasEnviadas postTarea(@RequestBody TareasEnviadas tareasEnviadas){
        return tareasEnviadasService.guardarTareasEnviadas(tareasEnviadas);
    }
}
