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
public class Tipo {
    private int id_tipo = 0;
    private String categoria;

    public Tipo (String categoria) {
        this.categoria = categoria;
    }
}
