package com.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.datavault.EmpleadoDv;
import com.datavault.jdbc.EmpleadoJdbcDv;
import com.model.Empleado;

public class EmpleadoService {
    private final EmpleadoDv empleadoDv;

    public EmpleadoService() throws SQLException {
        this.empleadoDv = new EmpleadoJdbcDv();
    }

    public Empleado crearEmpleado(Empleado em) throws Exception {
        if (em.getNombre() == null || em.getNombre().isEmpty()) {
            throw new Exception("el nombre no puede estar vacío");
        }
        if (em.getDocumento() == null || em.getDocumento().isEmpty()) {
            throw new Exception("el documento no puede estar vacío");
        }
        return empleadoDv.save(em);
    }

    public Optional<Empleado> buscarPorID(int id) throws Exception {
        return empleadoDv.findById(id);
    }

    public List<Empleado> listarEmpleados() throws Exception {
        return empleadoDv.findAll();
    }
    public void actualizarEmpleado(Empleado em) throws Exception {
        if (em.getId() == null) {
            throw new Exception("el id del empleado es obligatorio");
        }
        empleadoDv.update(em);
    }

    public void eliminarEmpleado(int id) throws Exception {
        empleadoDv.deleteById(id);
    }
}
