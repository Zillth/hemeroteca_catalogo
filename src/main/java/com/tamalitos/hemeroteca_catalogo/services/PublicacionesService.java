package com.tamalitos.hemeroteca_catalogo.services;

import java.sql.SQLException;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.models.Publicacion;
import com.tamalitos.hemeroteca_catalogo.models.PublicacionInner;
import com.tamalitos.hemeroteca_catalogo.repositories.PublicacionRepo;

import org.springframework.stereotype.Service;

@Service
public class PublicacionesService {
    private final PublicacionRepo publicacionesRepo;

    public PublicacionesService(PublicacionRepo publicacionesRepo) {
        this.publicacionesRepo = publicacionesRepo;
    }

    public List<Publicacion> getPublicaciones() throws SQLException {
        return this.publicacionesRepo.getPublicaciones();
    }

    public List<PublicacionInner> getPublicacionesInner() throws SQLException {
        return this.publicacionesRepo.getPublicacionesInner();
    }

    public Publicacion getPublicacionById(int id) throws SQLException {
        return this.publicacionesRepo.getPublicacionById(id);
    }

    public PublicacionInner getPublicacionInnerById(int id) throws SQLException {
        return this.publicacionesRepo.getPublicacionInnerById(id);
    }

    public List<PublicacionInner> getPublicacionesInnerByHemeroteca(int id_hemeroteca) throws SQLException {
        return this.publicacionesRepo.getPublicacionesInnerByHemeroteca(id_hemeroteca);
    }

    public void createPublicacion(Publicacion publicacion) throws SQLException {
        this.publicacionesRepo.createPublicacion(publicacion.getNombre(), publicacion.getImagen(),
                publicacion.getFecha_publicacion(),
                publicacion.getAutor(), publicacion.getDescripcion(), publicacion.getId_hemeroteca(),
                publicacion.getId_tipo());
    }

    public void modifyPublicacion(Publicacion publicacion) throws SQLException {
        this.publicacionesRepo.modifyPublicacion(publicacion.getId_publicacion(), publicacion.getNombre(),
                publicacion.getImagen(),
                publicacion.getFecha_publicacion(), publicacion.getAutor(), publicacion.getDescripcion(),
                publicacion.getId_hemeroteca(), publicacion.getId_tipo());
    }

    public void deletePublicacion(int id) throws SQLException {
        this.publicacionesRepo.deletePublicacion(id);
    }
}
