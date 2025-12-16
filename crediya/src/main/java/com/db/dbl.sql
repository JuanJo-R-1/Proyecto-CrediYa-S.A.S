CREATE DATABASE IF NOT EXISTS crediya_db;
USE crediya_db;

CREATE TABLE empleados (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(80),
  documento INT ,
  rol VARCHAR(30),
  correo VARCHAR(80),
  salario DECIMAL(10,2)
);

CREATE TABLE clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(80),
  documento INT,
  correo VARCHAR(80),
  telefono INT
);

CREATE TABLE prestamos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  clientes_id INT,
  empleados_id INT,
  monto DECIMAL(12,2),
  interes DECIMAL(5,2),
  cuotas INT,
  fecha_inicio DATE,
  estado VARCHAR(20),
  saldo_pendiente DECIMAL(12,2),
  FOREIGN KEY (clientes_id) REFERENCES clientes(id),
  FOREIGN KEY (empleados_id) REFERENCES empleados(id)
);

CREATE TABLE pagos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  prestamos_id INT,
  fecha_pago DATE,
  monto DECIMAL(10,2),
  FOREIGN KEY (prestamos_id) REFERENCES prestamos(id)
);
