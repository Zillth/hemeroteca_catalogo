package com.tamalitos.hemeroteca_catalogo.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.database.MySQLConnection;
import com.tamalitos.hemeroteca_catalogo.models.Tipo;

import org.springframework.stereotype.Repository;

@Repository
public class TipoRepo {
    final private MySQLConnection connection = new MySQLConnection();

    public List<Tipo> getTipos() throws SQLException {
        String sql = String.format("select * from Tipo");
        ResultSet s = this.connection.executeQuery(sql);
        List<Tipo> Tipos = new ArrayList<Tipo>();
        while (s.next()) {
            Tipos.add(
                    new Tipo(
                            s.getInt("id_tipo"),
                            s.getString("categoria")));
        }
        this.connection.disconnect();
        return Tipos;
    }

    public Tipo getTipoById(int id) throws SQLException {
        String sql = String.format("select * from Tipo where id_tipo = %d", id);
        ResultSet s = this.connection.executeQuery(sql);
        Tipo Tipo;
        if (s.next()) {
            Tipo = new Tipo(
                    s.getInt("id_tipo"),
                    s.getString("categoria"));
            this.connection.disconnect();
            return Tipo;
        }
        this.connection.disconnect();
        return new Tipo();
    }

    public void createTipo(String categoria) throws SQLException {
        String sql = String.format("insert into Tipo (categoria)" +
                " values ('%1$s')", categoria);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }

    public void modifyTipo(int id, String categoria) throws SQLException {
        String sql = String.format("update Tipo set " +
                "categoria = '%1$s' " +
                "where id_tipo = %2$d", categoria, id);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }

    public void deleteTipo(int id) throws SQLException {
        String sql = String.format("delete from Tipo where id_tipo = %d", id);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }
}
