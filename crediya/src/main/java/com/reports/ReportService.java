package com.reports;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

import com.model.Cliente;
import com.model.Pago;
import com.model.Prestamo;

public class ReportService {
    public List<Prestamo> prestamosActivos(List<Prestamo> prestamos) {
        return prestamos.stream()
        .filter(p -> "Pendiente".equalsIgnoreCase(p.getEstado()))
        .collect(Collectors.toList());
    }

    public List<Prestamo> prestamosPagados(List<Prestamo> prestamos) {
        return prestamos.stream()
            .filter(p -> "Pagado".equalsIgnoreCase(p.getEstado()))
            .collect(Collectors.toList());
    }

    public List<Cliente> clientesMorosos(List<Cliente> clientes, List<Prestamo> prestamos) {
        return prestamos.stream()
        .filter(p -> "Pendiente".equalsIgnoreCase(p.getEstado()))
        .map(Prestamo::getClienteId)
        .distinct()
        .flatMap(id -> clientes.stream()
            .filter(c -> c.getId().equals(id))
        )
        .collect(Collectors.toList());
    }

    public double totalPrestado(List<Prestamo> prestamos) {
        return prestamos.stream()
            .mapToDouble(Prestamo::getMonto)
            .sum();
    }

    public Map<Integer, Double> totalPagadoPorPrestamo(List<Pago> pagos) {
        return pagos.stream()
            .collect(Collectors.groupingBy(
                Pago::getIdPrestamo,
                Collectors.summingDouble(Pago::getMonto)
            ));
    }

    public List<Prestamo> prestamosVencidos(List<Prestamo> prestamos) {
        LocalDate hoy = LocalDate.now();

        return prestamos.stream()
            .filter(p -> "Pendiente".equalsIgnoreCase(p.getEstado()))
            .filter(p ->
                p.getFechaInicio()
                 .plusMonths(p.getCuotas())
                 .isBefore(hoy)
            )
            .collect(Collectors.toList());
    }
}
