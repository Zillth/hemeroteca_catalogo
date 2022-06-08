package com.tamalitos.hemeroteca_catalogo.controllers;

import java.sql.SQLException;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.models.Biblioteca;
import com.tamalitos.hemeroteca_catalogo.models.BibliotecaInner;
import com.tamalitos.hemeroteca_catalogo.services.BibliotecaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hemeroteca")
public class BibliotecaController {
    private final BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @GetMapping
    public ResponseEntity<List<Biblioteca>> getBibliotecas() {
        List<Biblioteca> bibliotecas = null;
        try {
            bibliotecas = this.bibliotecaService.getBibliotecas();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(bibliotecas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> getBibliotecas(@PathVariable("id") int id) {
        Biblioteca biblioteca = null;
        try {
            biblioteca = this.bibliotecaService.getBibliotecaById(id);
            if(biblioteca.getId_hemeroteca() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(biblioteca, HttpStatus.OK);
    }

    @GetMapping("/completa")
    public ResponseEntity<List<BibliotecaInner>> getBibliotecasInner() {
        List<BibliotecaInner> biblioteca = null;
        try {
            biblioteca = this.bibliotecaService.getBibliotecasInner();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(biblioteca, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createBiblioteca(@RequestBody Biblioteca biblioteca) {
        try {
            this.bibliotecaService.createBiblioteca(biblioteca);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyBiblioteca(@RequestBody Biblioteca biblioteca) {
        try {
            System.out.println(biblioteca.toString());
            this.bibliotecaService.modifyBiblioteca(biblioteca);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBiblioteca(@PathVariable("id") int id) {
        try {
            this.bibliotecaService.deleteBiblioteca(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
