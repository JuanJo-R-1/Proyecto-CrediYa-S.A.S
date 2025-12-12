package com.persistence;

import com.model.Empleado;
import com.model.Cliente;
import com.model.Pago;
import com.model.Prestamo;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FilePersistence {

    public static void saveEmpleados(List<Empleado> empleados, Path file) throws IOException {
        List<String> lines = empleados.stream()
            .map(e -> String.join(",", 
                  Optional.ofNullable(e.getId()).map(Object::toString).orElse(""),
                  e.getNombre(), e.getDocumento(), e.getRol(), e.getCorreo(), Double.toString(e.getSalario())
            ))
            .collect(Collectors.toList());
        Files.write(file, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<Empleado> loadEmpleados(Path file) throws IOException {
        if (!Files.exists(file)) return new ArrayList<>();
        return Files.lines(file)
            .map(line -> line.split(","))
            .filter(arr -> arr.length >= 6)
            .map(arr -> new Empleado(
                arr[0].isEmpty() ? null : Integer.valueOf(arr[0]),
                arr[1], arr[2], arr[3], arr[4],
                Double.parseDouble(arr[5])
            ))
            .collect(Collectors.toList());
    }

    public static void saveClientes(List<Cliente> clientes, Path file) throws IOException {
        List<String> lines = clientes.stream()
            .map(e -> String.join(",", 
                  Optional.ofNullable(e.getId()).map(Object::toString).orElse(""),
                  e.getNombre(), e.getDocumento(), e.getCorreo(), e.getTelefono()))
            .collect(Collectors.toList());
        Files.write(file, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<Cliente> loadClientes(Path file) throws IOException {
        if (!Files.exists(file)) return new ArrayList<>();
        return Files.lines(file)
            .map(line -> line.split(","))
            .filter(arr -> arr.length >= 6)
            .map(arr -> new Cliente(
                arr[0].isEmpty() ? null : Integer.valueOf(arr[0]),
                arr[1], arr[2], arr[3], arr[4]
            ))
            .collect(Collectors.toList());
    }

    public static void savePagos(List<Pago> pagos, Path file) throws IOException {
        List<String> lines = pagos.stream()
            .map(e -> String.join(",", 
                Optional.ofNullable(e.getId()).map(Object::toString).orElse(""),
                e.getIdPrestamo(), e.getFechaPago(), Double.toString(e.getMonto());
            ))
            .collect(Collectors.toList());
        Files.write(file, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<Pago> loadPagos(Path file) throws IOException {
        if (!Files.exists(file)) return new ArrayList<>();
        return Files.lines(file)
            .map(line -> line.split(","))
            .filter(arr -> arr.length >= 6)
            .map(arr -> new Pago(
                arr[0].isEmpty() ? null : Integer.valueOf(arr[0]),
                arr[1], arr[2]
                Double.parseDouble(arr[3])
            ))
            .collect(Collectors.toList());
    }

    public static void savePrestamos(List<Prestamo> prestamos, Path file) throws IOException {
        List<String> lines = prestamos.stream()
            .map(e -> String.join(",", 
                  Optional.ofNullable(e.getId()).map(Object::toString).orElse(""),
                  e.getClienteId(), e.getEmpleadoId(), e.getMonto(), e.getInteres(), e.getCuotas(), e.getFechaInicio(), e.getEstado()));
            .collect(Collectors.toList());
        Files.write(file, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<Prestamo> loadPretamos(Path file) throws IOException {
        if (!Files.exists(file)) return new ArrayList<>();
        return Files.lines(file)
            .map(line -> line.split(","))
            .filter(arr -> arr.length >= 6)
            .map(arr -> new Prestamo(
                arr[0].isEmpty() ? null : Integer.valueOf(arr[0]),
                arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]
                Double.parseDouble(arr[7], arr[8], arr[0])
            ))
            .collect(Collectors.toList());
    }
}

