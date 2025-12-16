package com.company;

import com.model.Cliente;
import com.model.Empleado;
import com.service.ClienteService;
import com.service.EmpleadoService;
import com.service.PagoService;
import com.service.PrestamoService;
import com.datavault.jdbc.PagoJdbcDv;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            ClienteService clienteService = new ClienteService();
            EmpleadoService empleadoService = new EmpleadoService();
            PrestamoService prestamoService = new PrestamoService();
            PagoService pagoService = new PagoService(new PagoJdbcDv());

            boolean salir = false;

            while (!salir) {

                System.out.println("\n======= CREDIYA S.A.S =======");
                System.out.println("1. Gestion de empleados");
                System.out.println("2. Gestion de clientes");
                System.out.println("3. Crear prestamo");
                System.out.println("4. Registrar pago");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opcion: ");

                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {

                    case 1:
                        System.out.println("\n--- EMPLEADOS ---");
                        System.out.println("1. Registrar empleado");
                        System.out.println("2. Listar empleados");
                        System.out.print("Opcion: ");
                        int opEmp = sc.nextInt();
                        sc.nextLine();

                        if (opEmp == 1) {
                            System.out.println("-----------------");
                            System.out.print("Nombre: ");
                            String nombre = sc.nextLine();
                            System.out.print("Documento: ");
                            String documento = sc.nextLine();
                            System.out.print("Rol: ");
                            String rol = sc.nextLine();
                            System.out.print("Correo: ");
                            String correo = sc.nextLine();
                            System.out.print("Salario: ");
                            double salario = sc.nextDouble();
                            System.out.println("-----------------");

                            Empleado empleado = new Empleado(
                                    null, nombre, documento, rol, correo, salario
                            );

                            empleadoService.crearEmpleado(empleado);
                            System.out.println("Empleado registrado correctamente");

                        } else if (opEmp == 2) {
                            empleadoService.listarEmpleados()
                                    .forEach(System.out::println);
                        }
                        break;

                    case 2:
                        System.out.println("\n--- CLIENTES ---");
                        System.out.println("1. Registrar cliente");
                        System.out.println("2. Listar clientes");
                        System.out.print("Opcion: ");
                        int opCli = sc.nextInt();
                        sc.nextLine();

                        if (opCli == 1) {
                            System.out.println("-----------------");
                            System.out.print("Nombre: ");
                            String nombre = sc.nextLine();
                            System.out.print("Documento: ");
                            String documento = sc.nextLine();
                            System.out.print("Correo: ");
                            String correo = sc.nextLine();
                            System.out.print("Telefono: ");
                            String telefono = sc.nextLine();
                            System.out.println("-----------------");
                            
                            Cliente cliente = new Cliente(
                                    null, nombre, documento, correo, telefono
                            );

                            clienteService.crearCliente(cliente);
                            System.out.println("Cliente registrado correctamente");

                        } else if (opCli == 2) {
                            clienteService.listarClientes()
                                    .forEach(System.out::println);
                        }
                        break;

                    case 3:
                        System.out.println("\n--- CREAR PRESTAMO ---");
                        System.out.print("ID Cliente: ");
                        int clienteId = sc.nextInt();
                        System.out.print("ID Empleado: ");
                        int empleadoId = sc.nextInt();
                        System.out.print("Monto: ");
                        double monto = sc.nextDouble();
                        System.out.print("Interes (%): ");
                        double interes = sc.nextDouble();
                        System.out.print("Cuotas: ");
                        int cuotas = sc.nextInt();

                        prestamoService.crearPrestamo(
                                clienteId, empleadoId, monto, interes, cuotas
                        );

                        System.out.println("Prestamo creado correctamente");
                        break;

                    case 4:
                        System.out.println("\n--- REGISTRAR PAGO ---");
                        System.out.print("ID Prestamo: ");
                        int prestamoId = sc.nextInt();
                        System.out.print("Monto a pagar: ");
                        double montoPago = sc.nextDouble();

                        pagoService.registrarPago(prestamoId, montoPago);
                        System.out.println("Pago registrado correctamente");
                        break;

                    case 5:
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opcion no valida");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
