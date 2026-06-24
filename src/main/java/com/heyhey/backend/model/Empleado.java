package com.heyhey.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @OneToOne
    @JoinColumn(
            name = "id_persona",
            referencedColumnName = "id_persona",
            nullable = false,
            unique = true
    )
    private Persona persona;

    @Column(columnDefinition = "int default 1") // asignacion del valor 1 en la variable
    private Integer estado = 1;
}
