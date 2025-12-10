package com.model;

import java.util.Objects;

public class Empleado {
    private Integer id;
    private String nombre, documento;
    private String rol, correo;
    private double salario;

    public Empleado(Integer id, String nombre, String documento, String rol, String correo, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.rol = rol;
        this.correo = correo;
        this.salario = salario;
    }

    public Integer getId() { 
        return id; 
    }
    
    public void setId(Integer id) {
        this.id = id; 
    }
    
    public String getNombre() {
        return nombre; 
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id = " + id + ", nombre = '" + nombre + '\'' + ", documento = '" + documento + '\'' +", rol = '" + rol + '\'' + ", correo = '" + correo + '\'' + ", salario = " + salario + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(id, empleado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

