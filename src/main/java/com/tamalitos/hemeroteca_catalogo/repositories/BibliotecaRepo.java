package com.tamalitos.hemeroteca_catalogo.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.database.MySQLConnection;
import com.tamalitos.hemeroteca_catalogo.models.Biblioteca;
import com.tamalitos.hemeroteca_catalogo.models.BibliotecaInner;

import org.springframework.stereotype.Repository;

@Repository
public class BibliotecaRepo {
    final private MySQLConnection connection = new MySQLConnection();

    public List<Biblioteca> getBibliotecas() throws SQLException {
        String sql = String.format("select * from Biblioteca");
        ResultSet s = this.connection.executeQuery(sql);
        List<Biblioteca> Bibliotecas = new ArrayList<Biblioteca>();
        while (s.next()) {
            Bibliotecas.add(
                    new Biblioteca(
                            s.getInt("id_hemeroteca"),
                            s.getString("nombre"),
                            s.getString("imagen"),
                            s.getString("horario_apertura"),
                            s.getString("dias_habiles"),
                            s.getString("direccion"),
                            s.getInt("id_ciudad")));
        }
        this.connection.disconnect();
        return Bibliotecas;
    }

    public List<BibliotecaInner> getBibliotecasInner() throws SQLException {
        String sql = String.format(
                "select b.id_hemeroteca, b.imagen, b.nombre nombre_hemeroteca, b.horario_apertura, b.dias_habiles, b.direccion, c.id_ciudad, c.nombre nombre_ciudad from biblioteca b inner join ciudad c on c.id_ciudad = b.id_ciudad");
        ResultSet s = this.connection.executeQuery(sql);
        List<BibliotecaInner> Bibliotecas = new ArrayList<BibliotecaInner>();
        while (s.next()) {
            Bibliotecas.add(
                    new BibliotecaInner(
                            s.getInt("id_hemeroteca"),
                            s.getString("imagen"),
                            s.getString("nombre_hemeroteca"),
                            s.getString("horario_apertura"),
                            s.getString("dias_habiles"),
                            s.getString("direccion"),
                            s.getInt("id_ciudad"),
                            s.getString("nombre_ciudad")));
        }
        this.connection.disconnect();
        return Bibliotecas;
    }

    public Biblioteca getBibliotecaById(int id) throws SQLException {
        String sql = String.format("select * from Biblioteca where id_hemeroteca = %d", id);
        ResultSet s = this.connection.executeQuery(sql);
        Biblioteca Biblioteca;
        if (s.next()) {
            Biblioteca = new Biblioteca(
                    s.getInt("id_hemeroteca"),
                    s.getString("nombre"),
                    s.getString("imagen"),
                    s.getString("horario_apertura"),
                    s.getString("dias_habiles"),
                    s.getString("direccion"),
                    s.getInt("id_ciudad"));
            this.connection.disconnect();
            return Biblioteca;
        }
        this.connection.disconnect();
        return new Biblioteca();
    }

    public BibliotecaInner getBibliotecaInnerById(int id) throws SQLException {
        String sql = String.format(
                "select b.id_hemeroteca, b.imagen, b.nombre nombre_hemeroteca, b.horario_apertura, b.dias_habiles, b.direccion, c.id_ciudad, c.nombre nombre_ciudad from Biblioteca where id_hemeroteca = %d",
                id);
        ResultSet s = this.connection.executeQuery(sql);
        BibliotecaInner Biblioteca;
        if (s.next()) {
            Biblioteca = new BibliotecaInner(
                    s.getInt("id_hemeroteca"),
                    s.getString("imagen"),
                    s.getString("nombre_hemeroteca"),
                    s.getString("horario_apertura"),
                    s.getString("dias_habiles"),
                    s.getString("direccion"),
                    s.getInt("id_ciudad"),
                    s.getString("nombre_ciudad"));
            this.connection.disconnect();
            return Biblioteca;
        }
        this.connection.disconnect();
        return new BibliotecaInner();
    }

    public void createBiblioteca(String nombre, String imagen, String horario, String dias, String direccion,
            int ciudad)
            throws SQLException {
        String sql = String
                .format("insert into Biblioteca (nombre, imagen, horario_apertura, dias_habiles, direccion, id_ciudad)"
                        +
                        " values ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s', %6$d)", nombre, imagen, horario, dias,
                        direccion, ciudad);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }

    public void modifyBiblioteca(int id, String nombre, String imagen, String horario, String dias, String direccion,
            int ciudad)
            throws SQLException {
        String sql = String.format("update Biblioteca set " +
                "nombre = '%1$s', imagen = '%2$s', horario_apertura = '%3$s', dias_habiles = '%4$s', direccion = '%5$s', id_ciudad = %6$d "
                +
                "where id_hemeroteca = %7$d", nombre, imagen, horario, dias, direccion, ciudad, id);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }

    public void deleteBiblioteca(int id) throws SQLException {
        String sql = String.format("delete from Biblioteca where id_hemeroteca = %d", id);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }
}
