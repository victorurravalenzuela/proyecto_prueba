package com.example.eventix.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eventix {
    @NotNull (message = "El campo Id no debe estar en blanco")
    private int idEvento;

    @NotBlank (message = "hola")
    private int apellido;

    private String caca;
    private String hola;

    //codigo cata sjodhñoasnddvisbadv

    @NotBlank (message = "El campo nombre evento no debe estar en blanco")
    private String nombreEvento;

    @NotBlank (message = "El campo tipo evento no debe estar en blanco")
    private String tipoEvento;

    @NotBlank (message = "El campo fecha evento no debe estar en blanco")
    private String fechaEvento;

    //hola

    @NotBlank (message = "El campo ubicacion evento no debe estar en blanco")
    private String ubicacionEvento;

    @NotNull (message = "El campo capacidad evento no debe estar en blanco")
    private int capacidadEvento;
}

//aca va otro codigo