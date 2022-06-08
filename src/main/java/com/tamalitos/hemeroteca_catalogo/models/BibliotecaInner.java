package com.tamalitos.hemeroteca_catalogo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BibliotecaInner extends Biblioteca {

    private String nombre_ciudad;

    public BibliotecaInner() {
        super();
    }

    public BibliotecaInner(int id, String imagen, String nombre, String horario, String dias, String direccion,
            int ciudad, String nombre_ciudad) {
        super();
        this.setId_hemeroteca(id);
        this.setImagen(imagen);
        this.setNombre(nombre);
        this.setHorario_apertura(horario);
        this.setDias_habiles(dias);
        this.setDireccion(direccion);
        this.setId_ciudad(ciudad);
        this.setNombre_ciudad(nombre_ciudad);
    }
}
