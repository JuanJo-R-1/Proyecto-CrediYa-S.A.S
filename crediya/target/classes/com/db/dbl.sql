-- CREACION DE BASE DE DATOS
CREATE DATABASE IF NOT EXISTS crediya_db;

USE crediya_db;

-- TABLA EMPLEADOS
CREATE TABLE empleados (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(80) NOT NULL,
  documento VARCHAR(30) NOT NULL,
  rol VARCHAR(30) NOT NULL,
  correo VARCHAR(80),
  salario DECIMAL(10,2)
);

-- TABLA CLIENTES
CREATE TABLE clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(80) NOT NULL,
  documento VARCHAR(30) NOT NULL,
  correo VARCHAR(80),
  telefono VARCHAR(20)
);

-- TABLA PRESTAMOS
CREATE TABLE prestamos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cliente_id INT NOT NULL,
  empleado_id INT NOT NULL,
  monto DECIMAL(12,2) NOT NULL,
  saldo_pendiente DECIMAL(12,2) NOT NULL,
  interes DECIMAL(5,2),
  cuotas INT,
  fecha_inicio DATE,
  estado VARCHAR(20),
  FOREIGN KEY (cliente_id) REFERENCES clientes(id),
  FOREIGN KEY (empleado_id) REFERENCES empleados(id)
);

-- TABLA PAGOS
CREATE TABLE pagos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  prestamo_id INT NOT NULL,
  fecha_pago DATE NOT NULL,
  monto DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (prestamo_id) REFERENCES prestamos(id)
);
