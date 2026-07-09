package com.heyhey.backend.controller;

import com.heyhey.backend.model.*;
import com.heyhey.backend.service.TareaService;
import com.heyhey.backend.service.TareasEnviadasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/tarea")
@CrossOrigin(origins = "*") // Importante para evitar bloqueos de red con Next.js
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private TareasEnviadasService tareasEnviadasService;

    /* * DTO (Data Transfer Object) interno.
     * Sirve exclusivamente para atrapar el JSON exacto que mandamos desde Next.js
     */
    public static class TareaRegistroDTO {
        public String titulo;
        public String descripcion;
        public Integer idCategoria;
        public Integer idEmpleado;
        public Integer idAdministrador;
        public Integer estado;
        public String fechaLimite;
    }

    @PostMapping
    public ResponseEntity<?> crearTareaYAsignar(@RequestBody TareaRegistroDTO request) {
        try {
            // 1. Crear y estructurar la entidad Tarea principal
            Tarea nuevaTarea = new Tarea();
            nuevaTarea.setTitulo(request.titulo);
            nuevaTarea.setDescripcion(request.descripcion);

            // Asignar Categoría (Ajusta los 'set' dependiendo de si usaste objetos o enteros en tu Modelo)
            CategoriaTarea categoria = new CategoriaTarea();
            categoria.setIdCategoria(request.idCategoria);
            nuevaTarea.setCategoria(categoria);

            // Guardamos la tarea en la base de datos (y obtenemos la entidad ya con el ID autogenerado)
            Tarea tareaGuardada = tareaService.gurdarTarea(nuevaTarea);

            // 2. Formatear la fecha. Next.js manda "YYYY-MM-DD", tu base de datos ocupa DATETIME
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime fechaFormateada = LocalDateTime.parse(request.fechaLimite + " 23:59:59", formatter);

            // 3. Crear y registrar la asignación en TareasEnviadas
            TareasEnviadas envio = new TareasEnviadas();
            envio.setTarea(tareaGuardada);

            Empleado empleado = new Empleado();
            empleado.setIdEmpleado(request.idEmpleado);
            envio.setEmpleado(empleado);

            Administrador admin = new Administrador();
            admin.setIdAdministrador(request.idAdministrador);
            envio.setAdministrador(admin);

            envio.setEstado(request.estado);
            envio.setFechaLimite(fechaFormateada);

            // Guardamos la relación en la base de datos
            tareasEnviadasService.guardarTareasEnviadas(envio);

            // Retornamos un JSON de éxito al Front-End
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"mensaje\": \"Tarea y asignación registradas con éxito\"}");

        } catch (Exception e) {
            // Si algo falla, el Front-End recibirá este mensaje para mostrar el error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error interno: " + e.getMessage() + "\"}");
        }
    }
}