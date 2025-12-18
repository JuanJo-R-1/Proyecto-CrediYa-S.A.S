package com.service;
//Posee una gran similitud con la herramienta de PagoJDBCDv.java, ademas de usar
// datos de PrestamoService. la vd parte del examen ya está hecho, 
// pero lo unico que tenía problema era en guardar la información en la base de datos
// 1. solicitar informacion del pago y verificar existencia, junto a la excepcion SQLException, se encuentra en PagoJDBCDv
// 2. Actualizar el saldo y revisar/cambiar el estado del prestamo se encuentra en PrestamoService.
//     Donde lo registra como pendiente, pero al pagarlo, lo cambia a pagado = CANCELADO
// 3. dentro de PagoDv y PagoJDBCDv se encuentra funciones que no terminé de implementar
//    Entre esas se encuentra la de Buscar un prestamo por id, o mostrar todos los prestamos
//    

/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement
import com.util.DBConnection;

public class GestorPagos {
    private final Connection conn;

    public GestorPagos() throws SQLException {
        this.conn = DBConnection.getConnection();
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
}
*/
