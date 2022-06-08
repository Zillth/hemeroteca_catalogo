package com.tamalitos.hemeroteca_catalogo.controllers;

import java.sql.SQLException;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.models.Publicacion;
import com.tamalitos.hemeroteca_catalogo.models.PublicacionInner;
import com.tamalitos.hemeroteca_catalogo.services.PublicacionesService;

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
@RequestMapping("/publicacion")
public class PublicacionController {
    private final PublicacionesService publicacionesService;

    public PublicacionController(PublicacionesService publicacionesService) {
        this.publicacionesService = publicacionesService;
    }

    @GetMapping
    public ResponseEntity<List<Publicacion>> getPublicaciones() {
        List<Publicacion> publicacions = null;
        try {
            publicacions = this.publicacionesService.getPublicaciones();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(publicacions, HttpStatus.OK);
    }

    @GetMapping("/completa")
    public ResponseEntity<List<PublicacionInner>> getPublicacionesCompletas() {
        List<PublicacionInner> publicacions = null;
        try {
            publicacions = this.publicacionesService.getPublicacionesInner();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(publicacions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> getPublicaciones(@PathVariable("id") int id) {
        Publicacion publicacion = null;
        try {
            publicacion = this.publicacionesService.getPublicacionById(id);
            if(publicacion.getId_publicacion() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(publicacion, HttpStatus.OK);
    }

    @GetMapping("/hemeroteca/{id}")
    public ResponseEntity<List<PublicacionInner>> getPublicacionesByHemeroteca(@PathVariable("id") int id) {
        List<PublicacionInner> publicacion = null;
        try {
            publicacion = this.publicacionesService.getPublicacionesInnerByHemeroteca(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(publicacion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createPublicacion(@RequestBody Publicacion publicacion) {
        publicacion.getFecha_publicacion().setTime(publicacion.getFecha_publicacion().getTime() + 100000000);
        try {
            this.publicacionesService.createPublicacion(publicacion);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modifyPublicacion(@RequestBody Publicacion publicacion) {
        publicacion.getFecha_publicacion().setTime(publicacion.getFecha_publicacion().getTime() + 100000000);
        try {
            this.publicacionesService.modifyPublicacion(publicacion);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublicacion(@PathVariable("id") int id) {
        try {
            this.publicacionesService.deletePublicacion(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
