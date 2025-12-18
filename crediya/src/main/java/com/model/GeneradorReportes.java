package com.model;
import java.util.Objects;
public class GeneradorReportes {
    private Integer id;
    private String tipoReporte;
    private String fechaGeneracion;
    private Cliente cliente;

    public GeneradorReportes(Integer id, String tipoReporte, String fechaGeneracion, Cliente cliente) {
        this.id = id;
        this.tipoReporte = tipoReporte;
        this.fechaGeneracion = fechaGeneracion;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Double.compare(empleado.salario, salario) == 0 && Objects.equals(id, empleado.id) && Objects.equals(nombre, empleado.nombre) && Objects.equals(departamento, empleado.departamento);
    }