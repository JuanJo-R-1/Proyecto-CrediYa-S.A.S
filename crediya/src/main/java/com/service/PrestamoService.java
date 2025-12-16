package com.service;

import com.model.Pago;
import com.model.Prestamo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoService {

    private List<Prestamo> prestamos = new ArrayList<>();
    private List<Pago> pagos = new ArrayList<>();

    public Prestamo crearPrestamo(
        
            Integer clienteId,
            Integer empleadoId,
            double monto,
            double interes,
            int cuotas
    ) {
        Prestamo prestamo = new Prestamo(
                null,
                clienteId,
                empleadoId,
                monto,
                interes,
                cuotas,
                LocalDate.now(),
                "Pendiente"
        );
        return prestamo;
    }

    public void registrarPago(Integer prestamoId, double montoPago) {
        Pago pago = new Pago(
                null,
                prestamoId,
                LocalDate.now(),
                montoPago
        );
        pagos.add(pago);
        double totalPagado = pagos.stream()
                .filter(p -> prestamoId.equals(p.getIdPrestamo()))
                .mapToDouble(Pago::getMonto)
                .sum();

        Prestamo prestamo = prestamos.stream()
                .filter(p -> prestamoId.equals(p.getId()))
                .findFirst()
                .orElse(null);

        if (prestamo != null) {
            double totalConInteres = prestamo.getMonto()
                    + (prestamo.getMonto() * prestamo.getInteres() / 100);

            if (totalPagado >= totalConInteres) {
                prestamo.setEstado("Pagado");
            }
        }
    }
}