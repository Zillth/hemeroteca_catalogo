package com.tamalitos.hemeroteca_catalogo.services;

import java.sql.SQLException;
import java.util.List;

import com.tamalitos.hemeroteca_catalogo.models.Admin;
import com.tamalitos.hemeroteca_catalogo.repositories.AdminRepo;

import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepo adminRepo;

    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Admin verifyAdmin(String username, String password) throws SQLException {
        return this.adminRepo.verifyAdmin(username, password);
    }

    public List<Admin> getAdmins() throws SQLException {
        return this.adminRepo.getAdmins();
    }

    public Admin getAdminById(int id) throws SQLException {
        return this.adminRepo.getAdminById(id);
    }

    public void createAdmin(Admin admin) throws SQLException {
        this.adminRepo.createAdmin(admin.getUsername(), admin.getPassword());
    }

    public void modifyAdmin(Admin admin) throws SQLException {
        this.adminRepo.modifyAdmin(admin.getId_admin(), admin.getUsername());
    }

    public void deleteAdmin(int id) throws SQLException {
        this.adminRepo.deleteAdmin(id);
    }
}
