package com.tamalitos.hemeroteca_catalogo.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.database.MySQLConnection;
import com.tamalitos.hemeroteca_catalogo.models.Ciudad;

import org.springframework.stereotype.Repository;

@Repository
public class CiudadRepo {
    final private MySQLConnection connection = new MySQLConnection();

    public List<Ciudad> getCiudades() throws SQLException {
        String sql = String.format("select * from Ciudad");
        ResultSet s = this.connection.executeQuery(sql);
        List<Ciudad> Ciudades = new ArrayList<Ciudad>();
        while (s.next()) {
            Ciudades.add(
                    new Ciudad(
                            s.getInt("id_ciudad"),
                            s.getString("nombre")));
        }
        this.connection.disconnect();
        return Ciudades;
    }

    public Ciudad getCiudadById(int id) throws SQLException {
        String sql = String.format("select * from Ciudad where id_ciudad = %d", id);
        ResultSet s = this.connection.executeQuery(sql);
        Ciudad Ciudad;
        if (s.next()) {
            Ciudad = new Ciudad(
                    s.getInt("id_ciudad"),
                    s.getString("nombre"));
            this.connection.disconnect();
            return Ciudad;
        }
        this.connection.disconnect();
        return new Ciudad();
    }

    public void createCiudad(String nombre) throws SQLException {
        String sql = String.format("insert into Ciudad (nombre)" +
                " values ('%1$s')", nombre);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }

    public void modifyCiudad(int id, String nombre) throws SQLException {
        String sql = String.format("update Ciudad set " +
                "nombre = '%1$s' " +
                "where id_ciudad = %2$d", nombre, id);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }

    public void deleteCiudad(int id) throws SQLException {
        String sql = String.format("delete from Ciudad where id_ciudad = %d", id);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }
}
