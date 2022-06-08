package com.tamalitos.hemeroteca_catalogo.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.database.MySQLConnection;
import com.tamalitos.hemeroteca_catalogo.models.Admin;

import org.springframework.stereotype.Repository;

@Repository
public class AdminRepo {
    final private MySQLConnection connection = new MySQLConnection();

    public Admin verifyAdmin(String username, String password) throws SQLException {
        String sql = String.format("call verifyAdmin('%1$s', '%2$s')", username, password);
        ResultSet s = this.connection.executeQuery(sql);
        Admin admin;
        if (s.next()) {
            admin = new Admin(
                    s.getInt("id_admin"),
                    s.getString("username"),
                    s.getString("password"));
            this.connection.disconnect();
            return admin;
        }
        this.connection.disconnect();
        return new Admin();
    }

    public List<Admin> getAdmins() throws SQLException {
        String sql = String.format("select * from admin");
        ResultSet s = this.connection.executeQuery(sql);
        List<Admin> admins = new ArrayList<Admin>();
        while (s.next()) {
            admins.add(
                    new Admin(
                            s.getInt("id_admin"),
                            s.getString("username"),
                            s.getString("password")));
        }
        this.connection.disconnect();
        return admins;
    }

    public Admin getAdminById(int id) throws SQLException {
        String sql = String.format("select * from admin where id_admin = %d", id);
        ResultSet s = this.connection.executeQuery(sql);
        Admin admin;
        if (s.next()) {
            admin = new Admin(
                    s.getInt("id_admin"),
                    s.getString("username"),
                    s.getString("password"));
            this.connection.disconnect();

            return admin;
        }
        this.connection.disconnect();
        return new Admin();
    }

    public void createAdmin(String username, String password)
            throws SQLException {
        String sql = String.format("insert into admin (username, password)" +
                " values ('%1$s', '%2$s')", username, password);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }

    public void modifyAdmin(int id, String username) throws SQLException {
        String sql = String.format("update admin set " +
                "username = '%1$s' " +
                "where id_admin = %2$d", username, id);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }

    }

    public void deleteAdmin(int id) throws SQLException {
        String sql = String.format("delete from admin where id_admin = %d", id);
        int s = this.connection.executeUpdate(sql);
        if (s == 0) {
            this.connection.disconnect();
            throw new SQLException();
        }
    }
}
