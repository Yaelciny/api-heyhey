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

    @GetMapping("/estado/{estado}")
    public List<TareasEnviadas> getAllEstado(@PathVariable Integer estado){
        return tareasEnviadasService.obtenerPorEstado(estado);
    }

    @GetMapping("/empleado/{idEmpleado}")
    public List<TareasEnviadas> getAllEmpleado(@PathVariable Integer idEmpleado){
        return tareasEnviadasService.obtenerPorEmpleado(idEmpleado);
    }

    @PutMapping("/{id}/estado")
    public TareasEnviadas actualizarEstado(@PathVariable("id") Integer idEnvio, @RequestBody Integer nuevoEstado) {
        TareasEnviadas tarea = tareasEnviadasService.obtenerId(idEnvio);

        // Actualizamos el estado (1, 2 o 3)
        tarea.setEstado(nuevoEstado);

        return tareasEnviadasService.guardarTareasEnviadas(tarea);
    }
}
