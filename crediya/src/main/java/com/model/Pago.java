package com.model;

import java.time.LocalDate;

public class Pago {

    private int id;
    private int idPrestamo;
    private LocalDate fechaPago;
    private double monto;

        public Pago() {
    }

    public Pago(Integer id, Integer idPrestamo, LocalDate fechaPago, double monto) {
        this.id = id;
        this.idPrestamo = idPrestamo;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }


    // GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id=" + id +
                ", idPrestamo=" + idPrestamo +
                ", fechaPago=" + fechaPago +
                ", monto=" + monto +
                '}';
    }
}
