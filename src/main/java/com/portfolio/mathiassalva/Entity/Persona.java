package com.portfolio.mathiassalva.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100, message = "Longitud de Nombre Invalida")
    private String nombre;
    @NotNull
    @Size(min = 1, max = 100, message = "Longitud de Apellido Invalida")
    private String apellido;

    @Size(min = 1, max = 100, message = "Longitud de Imagen Invalida")
    private String img;
}
