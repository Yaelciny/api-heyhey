package com.heyhey.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tareas_enviadas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TareasEnviadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Integer idEnvio;

    @ManyToOne
    @JoinColumn(name = "id_tarea", referencedColumnName = "id_tarea", nullable = false)
    private Tarea tarea;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_administrador", referencedColumnName = "id_administrador", nullable = false)
    private Administrador administrador;

    @Column(columnDefinition = "int default 1")
    private Integer estado = 1;

    @Column(name = "fecha_limite", nullable = false)
    private LocalDateTime fechaLimite;
}
