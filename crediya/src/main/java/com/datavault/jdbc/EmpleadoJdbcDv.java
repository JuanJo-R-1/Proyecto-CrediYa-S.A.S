package com.datavault.jdbc;

import com.datavault.EmpleadoDv;
import com.model.Empleado;
import com.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoJdbcDv implements EmpleadoDv {

    private final Connection conn;

    public EmpleadoJdbcDv() throws SQLException {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public Empleado save(Empleado em) throws SQLException {
        String sql = "INSERT INTO empleados(nombre, documento, rol, correo, salario) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement Ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            Ps.setString(1, em.getNombre());
            Ps.setString(2, em.getDocumento());
            Ps.setString(3, em.getRol());
            Ps.setString(4, em.getCorreo());
            Ps.setDouble(5, em.getSalario());
            Ps.executeUpdate();
            try (ResultSet rs = Ps.getGeneratedKeys()) {
                if (rs.next()) em.setId(rs.getInt(1));
            }
        }
        return em;
    }

    @Override
    public Optional<Empleado> findById(int id) throws SQLException {
        String sql = "SELECT * FROM empleados WHERE id = ?";
        try (PreparedStatement Ps = conn.prepareStatement(sql)) {
            Ps.setInt(1, id);
            try (ResultSet Rs = Ps.executeQuery()) {
                if (Rs.next()) {
                    Empleado e = new Empleado(
                            Rs.getInt("id"),
                            Rs.getString("nombre"),
                            Rs.getString("documento"),
                            Rs.getString("rol"),
                            Rs.getString("correo"),
                            Rs.getDouble("salario")
                    );
                    return Optional.of(e);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Empleado> findAll() throws SQLException {
        String sql = "SELECT * FROM empleados";
        List<Empleado> list = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet Rs = st.executeQuery(sql)) {
            while (Rs.next()) {
                Empleado e = new Empleado(
                        Rs.getInt("id"),
                        Rs.getString("nombre"),
                        Rs.getString("documento"),
                        Rs.getString("rol"),
                        Rs.getString("correo"),
                        Rs.getDouble("salario")
                );
                list.add(e);
            }
        }
        return list;
    }

    @Override
    public void update(Empleado em) throws SQLException {
        String sql = "UPDATE empleados SET nombre=?, documento=?, rol=?, correo=?, salario=? WHERE id=?";
        try (PreparedStatement Ps = conn.prepareStatement(sql)) {
            Ps.setString(1, em.getNombre());
            Ps.setString(2, em.getDocumento());
            Ps.setString(3, em.getRol());
            Ps.setString(4, em.getCorreo());
            Ps.setDouble(5, em.getSalario());
            Ps.setInt(6, em.getId());
            Ps.executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM empleados WHERE id = ?";
        try (PreparedStatement Ps = conn.prepareStatement(sql)) {
            Ps.setInt(1, id);
            Ps.executeUpdate();
        }
    }
}