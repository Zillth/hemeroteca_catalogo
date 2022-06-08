package com.tamalitos.hemeroteca_catalogo.models;

import java.sql.Date;

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
public class Publicacion {
    private int id_publicacion = 0;
    private String nombre;
    private String imagen;
    private Date fecha_publicacion;
    private String autor;
    private String descripcion;
    private int id_hemeroteca;
    private int id_tipo;

    public Publicacion (String nombre,String imagen, Date fecha, String autor, String descripcion, int hemeroteca, int tipo) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.fecha_publicacion = fecha;
        this.autor = autor;
        this.descripcion = descripcion;
        this.id_hemeroteca = hemeroteca;
        this.id_tipo = tipo;
    }
}
