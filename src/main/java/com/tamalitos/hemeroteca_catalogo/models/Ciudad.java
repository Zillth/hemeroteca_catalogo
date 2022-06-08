package com.tamalitos.hemeroteca_catalogo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ciudad {
    private int id_ciudad = 0;
    private String nombre;

    public Ciudad (String nombre) {
        this.nombre = nombre;
    }
}
