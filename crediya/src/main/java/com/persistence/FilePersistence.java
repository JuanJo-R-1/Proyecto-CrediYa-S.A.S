package com.persistence;

import com.model.Empleado;
import com.model.Cliente;
import com.model.Pago;
import com.model.Prestamo;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
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
        .map(p -> String.join(",",
            String.valueOf(p.getId()),
            String.valueOf(p.getIdPrestamo()),
            p.getFechaPago().toString(),
            String.valueOf(p.getMonto())
        ))
        .collect(Collectors.toList());
    Files.write(file, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<Pago> loadPagos(Path file) throws IOException {
    if (!Files.exists(file)) return new ArrayList<>();

        return Files.lines(file)
            .map(line -> line.split(","))
            .filter(arr -> arr.length == 4)
            .map(arr -> new Pago(
                arr[0].isEmpty() ? null : Integer.valueOf(arr[0]),
                Integer.valueOf(arr[1]),
                LocalDate.parse(arr[2]),
                Double.parseDouble(arr[3])
            ))
        .collect(Collectors.toList());
    }

    public static void savePrestamos(List<Prestamo> prestamos, Path file) throws IOException {
    List<String> lines = prestamos.stream()
        .map((Prestamo p) -> String.join(",",
            String.valueOf(p.getClienteId()),
            String.valueOf(p.getEmpleadoId()),
            String.valueOf(p.getMonto()),
            String.valueOf(p.getInteres()),
            String.valueOf(p.getCuotas()),
            p.getFechaInicio().toString(),
            String.valueOf(p.getEstado())
        ))
        .collect(Collectors.toList());

    Files.write(file, lines,
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<Prestamo> loadPrestamos(Path file) throws IOException {
    if (Files.notExists(file)) {
        return new ArrayList<>();
    }

    return Files.lines(file)
        .map(line -> line.split(","))
        .filter(arr -> arr.length == 8)
        .map(arr -> {
            Prestamo p = new Prestamo();
            p.setClienteId(Integer.parseInt(arr[1].trim()));
            p.setEmpleadoId(Integer.parseInt(arr[2].trim()));
            p.setMonto(Double.parseDouble(arr[3].trim()));
            p.setInteres(Double.parseDouble(arr[4].trim()));
            p.setCuotas(Integer.parseInt(arr[5].trim()));
            p.setFechaInicio(LocalDate.parse(arr[6].trim()));
            p.setEstado(arr[7].trim());
            return p;
        })
        .collect(Collectors.toList());
    }
}
