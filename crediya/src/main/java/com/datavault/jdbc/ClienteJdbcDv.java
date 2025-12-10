package com.datavault.jdbc;

import com.datavault.ClienteDv;
import com.model.Cliente;
import com.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteJdbcDv implements ClienteDv {

    private final Connection conn;

    public ClienteJdbcDv() throws SQLException {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public Cliente save(Cliente cl) throws SQLException {
        String sql = "INSERT INTO cliente (nombre, documento, correo, telefono) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getDocumento());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cl.setId(rs.getInt(1));
                }
            }
        }

        return cl;
    }

    @Override
    public Optional<Cliente> findById(int id) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("documento"),
                            rs.getString("correo"),
                            rs.getString("telefono")
                    );

                    return Optional.of(c);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Cliente> findAll() throws SQLException {
        String sql = "SELECT * FROM cliente";
        List<Cliente> list = new ArrayList<>();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("documento"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );
                list.add(c);
            }
        }

        return list;
    }

    @Override
    public void update(Cliente cl) throws SQLException {
        String sql = "UPDATE cliente SET nombre=?, documento=?, correo=?, telefono=? WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getDocumento());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());
            ps.setInt(5, cl.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
