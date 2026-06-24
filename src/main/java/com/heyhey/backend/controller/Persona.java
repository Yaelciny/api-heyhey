package com.heyhey.backend.controller;

//Importacion todas las herramientas de jakarta
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persona")
@Data // con esto se crean todos los getter y setter para las variables
@NoArgsConstructor // crea un constructor vacio invisible
@AllArgsConstructor // crea un contructor con todos los campos listos
public class Persona {
    @Id // es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // el equivalente a AUTO_INCREMENT
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(length = 20)
    private String telefono;

    @Column(nullable = false, length = 100, unique = true)
    private String correo;

    @Column(nullable = false, length = 255)
    private String contrasenia;

}
