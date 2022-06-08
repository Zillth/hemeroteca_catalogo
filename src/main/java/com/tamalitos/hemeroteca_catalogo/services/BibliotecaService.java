package com.tamalitos.hemeroteca_catalogo.services;

import java.sql.SQLException;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.models.Biblioteca;
import com.tamalitos.hemeroteca_catalogo.models.BibliotecaInner;
import com.tamalitos.hemeroteca_catalogo.repositories.BibliotecaRepo;

import org.springframework.stereotype.Service;

@Service
public class BibliotecaService {
    private final BibliotecaRepo bibliotecaRepo;

    public BibliotecaService(BibliotecaRepo bibliotecaRepo) {
        this.bibliotecaRepo = bibliotecaRepo;
    }

    public List<Biblioteca> getBibliotecas() throws SQLException {
        return this.bibliotecaRepo.getBibliotecas();
    }

    public List<BibliotecaInner> getBibliotecasInner() throws SQLException {
        return this.bibliotecaRepo.getBibliotecasInner();
    }

    public Biblioteca getBibliotecaById(int id) throws SQLException {
        return this.bibliotecaRepo.getBibliotecaById(id);
    }

    public Biblioteca getBibliotecaInnerById(int id) throws SQLException {
        return this.bibliotecaRepo.getBibliotecaInnerById(id);
    }

    public void createBiblioteca(Biblioteca Biblioteca) throws SQLException {
        this.bibliotecaRepo.createBiblioteca(Biblioteca.getNombre(), Biblioteca.getImagen(),
                Biblioteca.getHorario_apertura(), Biblioteca.getDias_habiles(), Biblioteca.getDireccion(),
                Biblioteca.getId_ciudad());
    }

    public void modifyBiblioteca(Biblioteca Biblioteca) throws SQLException {
        this.bibliotecaRepo.modifyBiblioteca(Biblioteca.getId_hemeroteca(), Biblioteca.getNombre(),
                Biblioteca.getImagen(),
                Biblioteca.getHorario_apertura(), Biblioteca.getDias_habiles(), Biblioteca.getDireccion(),
                Biblioteca.getId_ciudad());
    }

    public void deleteBiblioteca(int id) throws SQLException {
        this.bibliotecaRepo.deleteBiblioteca(id);
    }
}
