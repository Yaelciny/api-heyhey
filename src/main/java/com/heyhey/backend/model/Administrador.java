package com.heyhey.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrador")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador")
    private Integer idAdministrador;

    @OneToOne //Indica la realcion de uno a uno
    @JoinColumn(
            name = "id_persona",
            referencedColumnName = "id_persona",
            nullable = false,
            unique = true
    )//Es la FOREIGN KEY
    private Persona persona;
}
