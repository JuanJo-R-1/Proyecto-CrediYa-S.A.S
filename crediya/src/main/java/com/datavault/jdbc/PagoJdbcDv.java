package com.datavault.jdbc;

import com.datavault.PagoDv;
import com.model.Pago;
import com.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PagoJdbcDv implements PagoDv {

    private final Connection conn;

    public PagoJdbcDv() throws SQLException {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public Pago save(Pago pago) throws SQLException {
        String sql = "INSERT INTO pagos (prestamo_id, fecha_pago, monto) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pago.getIdPrestamo());
            ps.setDate(2, Date.valueOf(pago.getFechaPago()));
            ps.setDouble(3, pago.getMonto());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pago.setId(rs.getInt(1));
                }
            }
        }

        String updatePrestamoSql =
                "UPDATE prestamos SET saldo_pendiente = saldo_pendiente - ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(updatePrestamoSql)) {
            ps.setDouble(1, pago.getMonto());
            ps.setInt(2, pago.getIdPrestamo());
            ps.executeUpdate();
        }

        return pago;
    }

    @Override
    public Optional<Pago> findById(int id) throws SQLException {
        String sql = "SELECT * FROM pagos WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pago pago = new Pago();
                    pago.setId(rs.getInt("id"));
                    pago.setIdPrestamo(rs.getInt("prestamo_id"));

                    Date fechaSQL = rs.getDate("fecha_pago");
                    pago.setFechaPago(fechaSQL != null ? fechaSQL.toLocalDate() : null);

                    pago.setMonto(rs.getDouble("monto"));
                    return Optional.of(pago);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Pago> findAll() throws SQLException {
        String sql = "SELECT * FROM pagos";
        List<Pago> list = new ArrayList<>();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Pago pago = new Pago();
                pago.setId(rs.getInt("id"));
                pago.setIdPrestamo(rs.getInt("prestamo_id"));
                pago.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
                pago.setMonto(rs.getDouble("monto"));

                list.add(pago);
            }
        }
        return list;
    }

    @Override
    public List<Pago> findByPrestamoId(int prestamoId) throws SQLException {
        String sql = "SELECT * FROM pagos WHERE prestamo_id = ?";
        List<Pago> list = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, prestamoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pago pago = new Pago();
                    pago.setId(rs.getInt("id"));
                    pago.setIdPrestamo(rs.getInt("prestamo_id"));
                    pago.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
                    pago.setMonto(rs.getDouble("monto"));

                    list.add(pago);
                }
            }
        }
        return list;
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM pagos WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

   @Override
public void update(Pago pg) throws SQLException {
    String sql = "UPDATE pagos SET prestamo_id=?, fecha_pago=?, monto=? WHERE id=?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, pg.getIdPrestamo());
        ps.setDate(2, Date.valueOf(pg.getFechaPago()));
        ps.setDouble(3, pg.getMonto());
        ps.setInt(4, pg.getId());
        ps.executeUpdate();
    }
}

}
