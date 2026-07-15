package com.heyhey.backend.controller;

import com.heyhey.backend.model.Empleado;
import com.heyhey.backend.model.Persona;
import com.heyhey.backend.repository.EmpleadoRepository;
import com.heyhey.backend.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PersonaRepository personaRepository;

    /*
     * DTO para atrapar los datos combinados
     */
    public static class EmpleadoDTO {
        public String nombre;
        public String apellidos;
        public String telefono;
        public String correo;
        public String contrasenia;
    }

    // 1. LEER: Obtener todos los empleados (Solo los activos: estado = 1)
    @GetMapping
    public List<Empleado> obtenerEmpleadosActivos() {
        // Usamos un filtro básico en Java sobre el findAll() estándar.
        return empleadoRepository.findAll().stream()
                .filter(emp -> emp.getEstado() == 1)
                .toList();
    }

    // Registrar Persona y luego a Empleado
    @PostMapping
    @Transactional // Si algo falla en el proceso, hace un Rollback
    public ResponseEntity<?> crearEmpleado(@RequestBody EmpleadoDTO data) {
        try {
            // Primero creamos el registro en la tabla Persona
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre(data.nombre);
            nuevaPersona.setApellidos(data.apellidos);
            nuevaPersona.setTelefono(data.telefono);
            nuevaPersona.setCorreo(data.correo);
            nuevaPersona.setContrasenia(data.contrasenia);

            Persona personaGuardada = personaRepository.save(nuevaPersona);

            // Ahora creamos el registro en la tabla Empleado usando la persona guardada
            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setPersona(personaGuardada);
            nuevoEmpleado.setEstado(1); // 1 = Activo por defecto como en tu diseño de DB

            empleadoRepository.save(nuevoEmpleado);

            return ResponseEntity.status(HttpStatus.CREATED).body("{\"mensaje\": \"Empleado creado con éxito\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error al crear: " + e.getMessage() + "\"}");
        }
    }

    // 3. ACTUALIZAR: Modificar los datos personales del empleado
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizarEmpleado(@PathVariable("id") Integer idEmpleado, @RequestBody EmpleadoDTO data) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(idEmpleado);

        if (empleadoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Empleado no encontrado\"}");
        }

        try {
            Empleado empleado = empleadoOpt.get();
            Persona persona = empleado.getPersona(); // Obtenemos la persona ligada

            // Actualizamos solo los datos que vengan en la petición
            if (data.nombre != null) persona.setNombre(data.nombre);
            if (data.apellidos != null) persona.setApellidos(data.apellidos);
            if (data.telefono != null) persona.setTelefono(data.telefono);
            if (data.correo != null) persona.setCorreo(data.correo);
            if (data.contrasenia != null) persona.setContrasenia(data.contrasenia);

            personaRepository.save(persona);
            return ResponseEntity.ok("{\"mensaje\": \"Datos del empleado actualizados correctamente\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error al actualizar: " + e.getMessage() + "\"}");
        }
    }

    // 4. Cambiar el estado de 1 a 0
    @DeleteMapping("/{id}")
    public ResponseEntity<?> darDeBajaEmpleado(@PathVariable("id") Integer idEmpleado) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(idEmpleado);

        if (empleadoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Empleado no encontrado\"}");
        }

        Empleado empleado = empleadoOpt.get();
        empleado.setEstado(0); // Cambiamos el estatus a 0
        empleadoRepository.save(empleado);

        return ResponseEntity.ok("{\"mensaje\": \"Empleado dado de baja de manera lógica (Inactivo)\"}");
    }
}