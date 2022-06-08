package com.tamalitos.hemeroteca_catalogo.repositories;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.database.MySQLConnection;
import com.tamalitos.hemeroteca_catalogo.models.Publicacion;
import com.tamalitos.hemeroteca_catalogo.models.PublicacionInner;

import org.springframework.stereotype.Repository;

@Repository
public class PublicacionRepo {
    final private MySQLConnection connection = new MySQLConnection();

    public List<Publicacion> getPublicaciones() throws SQLException {
        String sql = String.format("select * from Publicacion");
        ResultSet s = this.connection.executeQuery(sql);
        List<Publicacion> Publicaciones = new ArrayList<Publicacion>();
        while (s.next()) {
            Publicaciones.add(
                    new Publicacion(
                            s.getInt("id_publicacion"),
                            s.getString("nombre"),
                            s.getString("imagen"),
                            s.getDate("fecha_publicacion"),
                            s.getString("autor"),
                            s.getString("descripcion"),
                            s.getInt("id_hemeroteca"),
                            s.getInt("id_tipo")));
        }
        this.connection.disconnect();
        return Publicaciones;
    }

    public List<PublicacionInner> getPublicacionesInner() throws SQLException {
        String sql = String.format(
                "select p.id_publicacion, p.nombre nombre_publicacion, p.imagen, p.fecha_publicacion, p.autor, p.descripcion, p.id_hemeroteca, b.nombre nombre_hemeroteca, p.id_tipo, t.categoria from publicacion p inner join biblioteca b on b.id_hemeroteca = p.id_hemeroteca inner join tipo t on t.id_tipo = p.id_tipo;");
        ResultSet s = this.connection.executeQuery(sql);
        List<PublicacionInner> Publicaciones = new ArrayList<PublicacionInner>();
        while (s.next()) {
            Publicaciones.add(
                    new PublicacionInner(
                            s.getInt("id_publicacion"),
                            s.getString("nombre_publicacion"),
                            s.getString("imagen"),
                            s.getDate("fecha_publicacion"),
                            s.getString("autor"),
                            s.getString("descripcion"),
                            s.getInt("id_hemeroteca"),
                            s.getString("nombre_hemeroteca"),
                            s.getInt("id_tipo"),
                            s.getString("categoria")));
        }
        this.connection.disconnect();
        return Publicaciones;
    }

    public Publicacion getPublicacionById(int id) throws SQLException {
        String sql = String.format("select * from Publicacion where id_publicacion = %d", id);
        ResultSet s = this.connection.executeQuery(sql);
        Publicacion Publicacion;
        if (s.next()) {
            Publicacion = new Publicacion(
                    s.getInt("id_publicacion"),
                    s.getString("nombre"),
                    s.getString("imagen"),
                    s.getDate("fecha_publicacion"),
                    s.getString("autor"),
                    s.getString("descripcion"),
                    s.getInt("id_hemeroteca"),
                    s.getInt("id_tipo"));
            this.connection.disconnect();
            return Publicacion;
        }
        this.connection.disconnect();
        return new Publicacion();
    }

    public PublicacionInner getPublicacionInnerById(int id) throws SQLException {
        String sql = String.format(
                "select p.id_publicacion, p.nombre nombre_publicacion, p.imagen, p.fecha_publicacion, p.autor, p.descripcion, p.id_hemeroteca, b.nombre nombre_hemeroteca, p.id_tipo, t.categoria from publicacion p inner join biblioteca b on b.id_hemeroteca = p.id_hemeroteca inner join tipo t on t.id_tipo = p.id_tipo where id_publicacion = %d",
                id);
        ResultSet s = this.connection.executeQuery(sql);
        PublicacionInner Publicacion;
        if (s.next()) {
            Publicacion = new PublicacionInner(
                    s.getInt("id_publicacion"),
                    s.getString("nombre_publicacion"),
                    s.getString("imagen"),
                    s.getDate("fecha_publicacion"),
                    s.getString("autor"),
                    s.getString("descripcion"),
                    s.getInt("id_hemeroteca"),
                    s.getString("nombre_hemeroteca"),
                    s.getInt("id_tipo"),
                    s.getString("categoria"));
            this.connection.disconnect();
            return Publicacion;
        }
        this.connection.disconnect();
        return new PublicacionInner();
    }

    public List<PublicacionInner> getPublicacionesInnerByHemeroteca(int id_hemeroteca) throws SQLException {
        String sql = String.format(
                "select p.id_publicacion, p.nombre nombre_publicacion, p.imagen, p.fecha_publicacion, p.autor, p.descripcion, p.id_hemeroteca, b.nombre nombre_hemeroteca, p.id_tipo, t.categoria from publicacion p inner join biblioteca b on b.id_hemeroteca = p.id_hemeroteca inner join tipo t on t.id_tipo = p.id_tipo where p.id_hemeroteca = %d",
                id_hemeroteca);
        ResultSet s = this.connection.executeQuery(sql);
        List<PublicacionInner> Publicacion = new ArrayList<>();
        while (s.next()) {
            Publicacion.add(new PublicacionInner(
                    s.getInt("id_publicacion"),
                    s.getString("nombre_publicacion"),
                    s.getString("imagen"),
                    s.getDate("fecha_publicacion"),
                    s.getString("autor"),
                    s.getString("descripcion"),
                    s.getInt("id_hemeroteca"),
                    s.getString("nombre_hemeroteca"),
                    s.getInt("id_tipo"),
                    s.getString("categoria")));

        }
        this.connection.disconnect();
        return Publicacion;
    }

    public void createPublicacion(String nombre, String imagen, Date fecha, String autor, String descripcion,
            int hemeroteca, int tipo)
            throws SQLException {
        String sql = String
                .format("insert into Publicacion (nombre, imagen, fecha_publicacion, autor, descripcion, id_hemeroteca, id_tipo)"
                        +
                        " values ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s', %6$d, %7$d)", nombre, imagen, fecha, autor,
                        descripcion,
                        hemeroteca, tipo);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }

    public void modifyPublicacion(int id, String nombre, String imagen, Date fecha, String autor, String descripcion,
            int hemeroteca,
            int tipo)
            throws SQLException {
        String sql = String.format("update Publicacion set " +
                "nombre = '%1$s', imagen = '%2$s', fecha_publicacion = '%3$s', autor = '%4$s', descripcion = '%5$s', id_hemeroteca = %6$d, id_tipo = %7$d "
                +
                "where id_publicacion = %8$d", nombre, imagen, fecha, autor, descripcion,
                hemeroteca, tipo, id);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }

    public void deletePublicacion(int id) throws SQLException {
        String sql = String.format("delete from Publicacion where id_publicacion = %d", id);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }
}
