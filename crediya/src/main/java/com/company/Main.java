package com.company;

import com.datavault.jdbc.EmpleadoJdbcDv;
import com.model.Empleado;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            EmpleadoJdbcDv empleadoJdbcDv = new EmpleadoJdbcDv();
            while (true) {
                System.out.println("\n ------ Crediya  -  Menu -----");
                System.out.println("Opciones:");
                System.out.println("1. Registrar un nuevo empleado");
                System.out.println("2. Revisar lista de empleados");
                System.out.println("3. salir");
                System.out.println("");
            }
        }
    }
}