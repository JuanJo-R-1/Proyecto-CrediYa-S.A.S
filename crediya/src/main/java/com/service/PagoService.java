package com.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.datavault.PagoDv;
import com.model.Pago;

public class PagoService {

    private final PagoDv pagoDv;

    public PagoService(PagoDv pagoDv) {
        this.pagoDv = pagoDv;
    }


    public Pago registrarPago(int prestamoId, double monto) throws Exception {

        if (monto <= 0) {
            throw new Exception("El monto debe ser mayor a cero");
        }

        Pago pago = new Pago();
        pago.setIdPrestamo(prestamoId);
        pago.setMonto(monto);
        pago.setFechaPago(LocalDate.now());

        return pagoDv.save(pago);
    }

    public Optional<Pago> buscarPagoPorId(int id) throws Exception {
        return pagoDv.findById(id);
    }

    public List<Pago> listarPagos() throws Exception {
        return pagoDv.findAll();
    }

    public List<Pago> listarPagosPorPrestamo(int prestamoId) throws Exception {
        return pagoDv.findByPrestamoId(prestamoId);
    }

    public void eliminarPago(int id) throws Exception {
        pagoDv.deleteById(id);
    }
}
