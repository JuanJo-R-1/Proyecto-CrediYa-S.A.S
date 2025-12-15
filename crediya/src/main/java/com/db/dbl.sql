CREATE DATABASE IF NOT EXISTS crediya_db;
USE crediya_db;

CREATE TABLE empleado (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(80),
  documento VARCHAR(30),
  rol VARCHAR(30),
  correo VARCHAR(80),
  salario DECIMAL(10,2)
);

CREATE TABLE cliente (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(80),
  documento VARCHAR(30),
  correo VARCHAR(80),
  telefono VARCHAR(20)
);

CREATE TABLE prestamos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cliente_id INT,
  empleado_id INT,
  monto DECIMAL(12,2),
  interes DECIMAL(5,2),
  cuotas INT,
  fecha_inicio DATE,
  estado VARCHAR(20),
  saldo_pendiente DECIMAL(12,2),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  FOREIGN KEY (empleado_id) REFERENCES empleado(id)
);

CREATE TABLE pagos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  prestamo_id INT,
  fecha_pago DATE,
  monto DECIMAL(10,2),
  FOREIGN KEY (prestamo_id) REFERENCES prestamos(id)
);
