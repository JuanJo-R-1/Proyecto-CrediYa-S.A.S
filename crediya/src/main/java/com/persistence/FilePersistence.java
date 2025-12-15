package com.persistence;

import com.model.Cliente;
import com.model.Empleado;
import com.model.Pago;
import com.model.Prestamo;

import java.io.FileWriter;
import java.io.IOException;

public class FilePersistence {

    private static final String RUTA = "src/main/resources/data/";

    public static void guardarEmpleado(Empleado e) throws IOException {
        FileWriter fw = new FileWriter(RUTA + "empleados.txt", true);
        fw.write(e.getId() + "," + e.getNombre() + "," + e.getDocumento() + ","
                + e.getRol() + "," + e.getCorreo() + "," + e.getSalario() + "\n");
        fw.close();
    }

    public static void guardarCliente(Cliente c) throws IOException {
        FileWriter fw = new FileWriter(RUTA + "clientes.txt", true);
        fw.write(c.getId() + "," + c.getNombre() + "," + c.getDocumento() + ","
                + c.getCorreo() + "," + c.getTelefono() + "\n");
        fw.close();
    }

    public static void guardarPrestamo(Prestamo p) throws IOException {
        FileWriter fw = new FileWriter(RUTA + "prestamos.txt", true);
        fw.write(p.getId() + "," + p.getClienteId() + "," + p.getEmpleadoId() + ","
                + p.getMonto() + "," + p.getInteres() + "," + p.getCuotas() + ","
                + p.getFechaInicio() + "," + p.getEstado() + "\n");
        fw.close();
    }

    public static void guardarPago(Pago p) throws IOException {
        FileWriter fw = new FileWriter(RUTA + "pagos.txt", true);
        fw.write(p.getId() + "," + p.getIdPrestamo() + ","
                + p.getFechaPago() + "," + p.getMonto() + "\n");
        fw.close();
    }
}
