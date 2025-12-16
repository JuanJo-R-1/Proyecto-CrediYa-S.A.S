## 📌 Sistema de Cobros de Cartera “CrediYa”

Este proyecto corresponde al desarrollo de un sistema de consola en Java para la empresa CrediYa S.A.S., la cual se dedica al otorgamiento de creditos personales.  
El sistema busca reemplazar el manejo manual de la informacion (como hojas de calculo) por una solucion organizada, modular y con persistencia en archivos y base de datos MySQL.

---

### 🎯 Objetivo del Proyecto

Desarrollar una aplicacion en Java que permita administrar empleados, clientes, prestamos y pagos, aplicando:

- Programacion Orientada a Objetos (POO)
- Uso de JDBC para conexion con MySQL
- Manejo de archivos de texto
- Buenas practicas de programacion y manejo de excepciones
- Arquitectura modular y organizada por capas

---

### 🧩 Modulos del Sistema

🔹 Modulo de Empleados  
Permite registrar, listar, actualizar y eliminar empleados.  
Cada empleado cuenta con: id, nombre, documento, rol, correo y salario.  
La informacion se almacena tanto en archivos como en la base de datos.

🔹 Modulo de Clientes  
Permite registrar y consultar clientes del sistema.  
Cada cliente tiene: id, nombre, documento, correo y telefono.  
Se pueden consultar los prestamos asociados a cada cliente.

🔹 Modulo de Prestamos  
Permite crear prestamos asociando un cliente y un empleado.  
El sistema calcula automaticamente:
- Interes del prestamo
- Estado del prestamo (pendiente o pagado)

Los prestamos se guardan en archivos y en la base de datos MySQL.

🔹 Modulo de Pagos  
Permite registrar pagos o abonos a un prestamo.  
Actualiza el saldo pendiente y mantiene el historial completo de pagos realizados.

🔹 Modulo de Reportes  
Permite generar reportes basicos como:
- Prestamos activos
- Prestamos pagados
- Historial de pagos

Se apoya en colecciones y Stream API para el filtrado de informacion.

---

### Funcionalidades Principales

- Menu interactivo por consola
- Persistencia mixta (archivos txt y MySQL)
- Calculo automatico de intereses
- Control de pagos y saldos
- Codigo organizado, claro y mantenible

---

### Tecnologias Utilizadas

- Java
- JDBC
- MySQL
- Programacion Orientada a Objetos
- Colecciones de Java
- Manejo de Archivos
- Stream API

---

### Ejecucion del Proyecto

1. Ejecutar el script SQL para crear la base de datos `crediya_db`.
2. Configurar los datos de conexion en la clase DBConnection.
3. Ejecutar la clase Main.java.
4. Usar el menu por consola para interactuar con el sistema.

---

### 📌 Conclusion

El sistema CrediYa permite gestionar de manera organizada los procesos de prestamos y pagos, aplicando los conceptos vistos en clase y ofreciendo una solucion clara, funcional y escalable.

