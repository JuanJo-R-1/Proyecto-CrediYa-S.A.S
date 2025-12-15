package com.datavault.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.model.Prestamo;
import com.datavault.PrestamoDv;
import com.util.DBConnection;

public class PrestamoJdbcDv implements PrestamoDv {
  private final Connection conn;

    public PrestamoJdbcDv() throws Exception {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public Prestamo saveP(Prestamo prestamo) throws Exception {
        String sql = "INSERT INTO prestamos (id, clienteId, empleadoId, monto, interes, saldo, cuotas, fechaInicio, estado) VALUES (?, ?, ?, ? , ? , ? , ? , ? , ? )";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, prestamo.getPId());
            ps.setInt(2, prestamo.getClienteId());
            ps.setInt(3, prestamo.getEmpleadoId());
            ps.setDouble(4, prestamo.getMonto());
            ps.setDouble(5, prestamo.getInteres());
            ps.setDouble(6, prestamo.getSaldo());
            ps.setInt(7, prestamo.getCuotas());
            ps.setDate(8, Date.valueOf(prestamo.getFechaInicio()));
            
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    prestamo.setPId(rs.getInt(1));
                }
            }
        }

        String updatePrestamoSql =
                "UPDATE prestamos SET saldo_pendiente = saldo_pendiente - ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(updatePrestamoSql)) {
            ps.setInt(1, prestamo.getPId());
            ps.setInt(2, prestamo.getClienteId());
            ps.setInt(3, prestamo.getEmpleadoId());
            ps.setDouble(4, prestamo.getMonto());
            ps.setDouble(5, prestamo.getInteres());
            ps.setDouble(6, prestamo.getSaldo());
            ps.setInt(7, prestamo.getCuotas());
            ps.executeUpdate();
        }

        return prestamo;
    }

    @Override
    public Optional<Prestamo> findPById(int id) throws Exception {
        String sql = "SELECT * FROM prestamos WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setPId(rs.getInt("id"));
                    prestamo.setClienteId(rs.getInt("ClienteId"));
                    prestamo.setEmpleadoId(rs.getInt("EmpleadoId"));
                    prestamo.setMonto(rs.getDouble("Monto"));
                    prestamo.setInteres(rs.getDouble("Interes"));
                    prestamo.setSaldo(rs.getDouble("Saldo"));
                    prestamo.setCuotas(rs.getInt("Cuotas"));
                    Date fechaSQL = rs.getDate("fecha_pago");
                    prestamo.setFechaInicio(fechaSQL != null ? fechaSQL.toLocalDate() : null);

                    return Optional.of(prestamo);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Prestamo> findPAll() throws Exception {
        String sql = "SELECT * FROM pagos";
        List<Prestamo> list = new ArrayList<>();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setPId(rs.getInt("id"));
                prestamo.setClienteId(rs.getInt("Cliente_id"));
                prestamo.setEmpleadoId(rs.getInt("Empleado_id"));
                prestamo.setMonto(rs.getDouble("monto"));
                prestamo.setInteres(rs.getDouble("Interes"));
                prestamo.setSaldo(rs.getDouble("Saldo"));
                prestamo.setCuotas(rs.getInt("Cuotas"));
                prestamo.setFechaInicio(rs.getDate("fecha_Inicio").toLocalDate());
                prestamo.setEstado(rs.getString("Estado"));
                list.add(prestamo);
            }
        }
        return list;
    }

    @Override
    public List<Prestamo> findPByPrestamoId(int id) throws Exception {
        String sql = "SELECT * FROM pretamos WHERE id = ?";
        List<Prestamo> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setPId(rs.getInt("id"));
                prestamo.setClienteId(rs.getInt("Cliente_id"));
                prestamo.setEmpleadoId(rs.getInt("Empleado_id"));
                prestamo.setMonto(rs.getDouble("monto"));
                prestamo.setInteres(rs.getDouble("Interes"));
                prestamo.setSaldo(rs.getDouble("Saldo"));
                prestamo.setCuotas(rs.getInt("Cuotas"));
                prestamo.setFechaInicio(rs.getDate("fecha_Inicio").toLocalDate());
                prestamo.setEstado(rs.getString("Estado"));

                list.add(prestamo);
                }
            }
        }
        return list;
    }

    @Override
    public void deletePById(int id) throws Exception {
        String sql = "DELETE FROM prestamos WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

   @Override
public void updateP(Prestamo prestamos) throws Exception {
    String sql = "UPDATE prestamos SET cliente_id=?, empleado_id=?, fecha_inicio=?, monto=?, interes=?, saldo=?, cuotas=?, estado=? WHERE id=?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, prestamos.getPId());
        ps.setInt(2, prestamos.getClienteId());
        ps.setInt(3, prestamos.getEmpleadoId());
        ps.setDouble(4, prestamos.getMonto());
        ps.setDouble(5, prestamos.getInteres());
        ps.setDouble(6, prestamos.getSaldo());
        ps.setInt(7, prestamos.getCuotas());
        ps.setDate(8, Date.valueOf(prestamos.getFechaInicio()));
        ps.setString(9, prestamos.getEstado());
        ps.executeUpdate();
    }
}

}