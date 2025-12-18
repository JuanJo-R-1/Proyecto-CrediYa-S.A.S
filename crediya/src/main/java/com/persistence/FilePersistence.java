package com.persistence;

import com.model.Cliente;
import com.model.Empleado;
import com.model.Pago;
import com.model.Prestamo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FilePersistence {

    private static final String RUTA = "src/main/resources/data/";

    public static void guardarEmpleados(List<Empleado> empleados, Path file) throws IOException {
    if (file.getParent() != null) {
        Files.createDirectories(file.getParent());
    }

    List<String> lines = empleados.stream()
        .map(e -> String.join(";",
            String.valueOf(e.getId()),
            e.getNombre(),
            e.getDocumento(),
            e.getRol(),
            e.getCorreo(),
            String.valueOf(e.getSalario())
        ))
        .toList();

    Files.write(file, lines,
        StandardOpenOption.CREATE,
        StandardOpenOption.APPEND
    );
    }


    public static List<Empleado> loadEmpleados(Path file) throws IOException {
    if (!Files.exists(file)) {
        return new ArrayList<>();
    }
    try (Stream<String> lines = Files.lines(file)) {
        return lines
            .map(line -> line.split(";"))
            .filter(arr -> arr.length == 6)
            .map(arr -> new Empleado(
                arr[0].isEmpty() ? null : Integer.parseInt(arr[0]),
                arr[1],
                arr[2],
                arr[3],
                arr[4],
                Double.parseDouble(arr[5])
            ))
            .toList();
        }
    }



    public static void guardarClientes(List<Cliente> clientes, Path file) throws IOException {
         List<String> lines = clientes.stream()
        .map(c -> String.join(",", 
            Optional.ofNullable(c.getId()).map(Object::toString).orElse(""),
            c.getNombre(),
            c.getDocumento(),
            c.getCorreo(),
            c.getTelefono()
        ))
        .collect(Collectors.toList());
        Files.write(file, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static List<Cliente> loadClientes(Path file) throws IOException {
    if (!Files.exists(file)) {
        return new ArrayList<>();
    }
    try (Stream<String> lines = Files.lines(file)) {
        return lines
            .map(line -> line.split(";"))
            .filter(arr -> arr.length == 5)
            .map(arr -> new Cliente(
                arr[0].isEmpty() ? null : Integer.parseInt(arr[0]),
                arr[1],
                arr[2],
                arr[3],
                arr[4]
            ))
            .toList();
        }
    }


    public static void guardarPrestamo(Prestamo p) throws IOException {
        FileWriter fw = new FileWriter(RUTA + "prestamos.txt", true);
        fw.write(p.getId() + "," + p.getClienteId() + "," + p.getEmpleadoId() + ","
                + p.getMonto() + "," + p.getInteres() + "," + p.getCuotas() + ","
                + p.getFechaInicio() + "," + p.getEstado() + "\n");
        fw.close();
    }

    public static List<Prestamo> loadPrestamos(Path file) throws IOException {
    if (!Files.exists(file)) {
        return new ArrayList<>();
    }
    try (Stream<String> lines = Files.lines(file)) {
        return lines
            .map(line -> line.split(";"))
            .filter(arr -> arr.length == 8)
            .map(arr -> new Prestamo(
                arr[0].isEmpty() ? null : Integer.parseInt(arr[0]),
                Integer.parseInt(arr[1]),
                Integer.parseInt(arr[2]),
                Double.parseDouble(arr[3]),
                Double.parseDouble(arr[4]),
                Integer.parseInt(arr[5]),
                LocalDate.parse(arr[6]),
                arr[7]
            ))
            .toList();
    }
    }

    public static void guardarPago(Pago p) throws IOException {
        FileWriter fw = new FileWriter(RUTA + "pagos.txt", true);
        fw.write(p.getId() + "," + p.getIdPrestamo() + ","
                + p.getFechaPago() + "," + p.getMonto() + "\n");
        fw.close();
    }

    public static List<Pago> loadPagos(Path file) throws IOException {
    if (!Files.exists(file)) {
        return new ArrayList<>();
    }
    try (Stream<String> lines = Files.lines(file)) {
        return lines
            .map(line -> line.split(";"))
            .filter(arr -> arr.length == 4)
            .map(arr -> new Pago(
                arr[0].isEmpty() ? null : Integer.parseInt(arr[0]),
                Integer.parseInt(arr[1]),
                LocalDate.parse(arr[2]),
                Double.parseDouble(arr[3])
            ))
            .toList();
        }
        }
}

