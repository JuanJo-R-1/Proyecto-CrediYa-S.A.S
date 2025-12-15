package com.model;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {
    private Integer id, clienteId, empleadoId;
    private double monto, interes;
    private int cuotas;
    private LocalDate fechaInicio;
    private String estado;

   public Prestamo() {
    }

    public Prestamo(
            Integer id,
            Integer clienteId,
            Integer empleadoId,
            double monto,
            double interes,
            int cuotas,
            LocalDate fechaInicio,
            String estado
    ) {
        this.id = id;
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.monto = monto;
        this.interes = interes;
        this.cuotas = cuotas;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
    }


    /*Getters y setters */
    public Integer getId () {
        return id;
    }

    public void setId(Integer id) {
        this.id = id; 
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double calcularMontoTotal() {
        double tasaAnual = interes / 100.0;
        double tiempoAnios = cuotas / 12.0;
        double interesTotal = monto * tasaAnual * tiempoAnios;
        return monto + interesTotal;
    }
    
    public double valorCuotaMensual() {
        return calcularMontoTotal()/cuotas;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "id = " + id + ", Cliente ID = '" + clienteId + '\'' + ", Empleado ID = '" + empleadoId + '\'' +", Monto = '" + monto +
        '\'' + ", Interes = '" + interes + '\'' + '\'' + ", Cuotas = " + cuotas + '\'' + ", Fecha Inicio = " + fechaInicio +
        '\'' + ", Estado = " + estado + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prestamo)) return false;
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(id, prestamo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}