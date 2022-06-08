package com.tamalitos.hemeroteca_catalogo.models;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PublicacionInner extends Publicacion {
    private String categoria;
    private String nombre_hemeroteca;

    public PublicacionInner(int id_publicacion, String nombre, String imagen, Date fecha, String autor, String descripcion, int hemeroteca,
            String nombre_hemeroteca, int tipo, String categoria) {
        super();
        this.setAutor(autor);
        this.setImagen(imagen);
        this.setCategoria(categoria);
        this.setDescripcion(descripcion);
        this.setFecha_publicacion(fecha);
        this.setId_hemeroteca(hemeroteca);
        this.setId_publicacion(id_publicacion);
        this.setId_tipo(tipo);
        this.setNombre(nombre);
        this.setNombre_hemeroteca(nombre_hemeroteca);
    }
}
