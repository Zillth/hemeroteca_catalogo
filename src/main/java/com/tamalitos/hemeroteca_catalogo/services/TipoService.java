package com.tamalitos.hemeroteca_catalogo.services;

import java.sql.SQLException;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.models.Tipo;
import com.tamalitos.hemeroteca_catalogo.repositories.TipoRepo;

import org.springframework.stereotype.Service;

@Service
public class TipoService {
    private final TipoRepo tipoRepo;

    public TipoService(TipoRepo tipoRepo) {
        this.tipoRepo = tipoRepo;
    }

    public List<Tipo> getTipos() throws SQLException {
        return this.tipoRepo.getTipos();
    }

    public Tipo getTipoById(int id) throws SQLException {
        return this.tipoRepo.getTipoById(id);
    }

    public void createTipo(Tipo tipo) throws SQLException {
        this.tipoRepo.createTipo(tipo.getCategoria());
    }

    public void modifyTipo(Tipo tipo) throws SQLException {
        this.tipoRepo.modifyTipo(tipo.getId_tipo(), tipo.getCategoria());
    }

    public void deleteTipo(int id) throws SQLException {
        this.tipoRepo.deleteTipo(id);
    }
}
