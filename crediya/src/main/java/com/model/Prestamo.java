package com.model;

import java.time.LocalDate;

public class Prestamo {
    private Integer id, clienteId, empleadoId;
    private double monto, interes;
    private int cuotas;
    private LocalDate fechaInicio;
    private String estado;
    private double saldo;
}
