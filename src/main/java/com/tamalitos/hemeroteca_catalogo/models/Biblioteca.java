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
public class Biblioteca {
    private int id_hemeroteca = 0;
    private String nombre;
    private String imagen;
    private String horario_apertura;
    private String dias_habiles;
    private String direccion;
    private int id_ciudad;

    public Biblioteca(String nombre, String imagen, String horario, String dias, String direccion, int ciudad) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.horario_apertura = horario;
        this.dias_habiles = dias;
        this.direccion = direccion;
        this.id_ciudad = ciudad;
    }
}