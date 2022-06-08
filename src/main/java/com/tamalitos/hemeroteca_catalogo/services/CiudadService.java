package com.tamalitos.hemeroteca_catalogo.services;

import java.sql.SQLException;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.models.Ciudad;
import com.tamalitos.hemeroteca_catalogo.repositories.CiudadRepo;

import org.springframework.stereotype.Service;

@Service
public class CiudadService {
    private final CiudadRepo ciudadRepo;

    public CiudadService(CiudadRepo ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    public List<Ciudad> getCiudades() throws SQLException {
        return this.ciudadRepo.getCiudades();
    }

    public Ciudad getCiudadById(int id) throws SQLException {
        return this.ciudadRepo.getCiudadById(id);
    }

    public void createCiudad(Ciudad ciudad) throws SQLException {
        this.ciudadRepo.createCiudad(ciudad.getNombre());
    }

    public void modifyCiudad(Ciudad ciudad) throws SQLException {
        this.ciudadRepo.modifyCiudad(ciudad.getId_ciudad(), ciudad.getNombre());
    }

    public void deleteCiudad(int id) throws SQLException {
        this.ciudadRepo.deleteCiudad(id);
    }
}
