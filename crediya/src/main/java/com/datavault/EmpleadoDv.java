package com.datavault;

import java.util.List;
import java.util.Optional;

import com.model.Empleado;

public interface EmpleadoDv {
    Empleado save(Empleado e) throws Exception;
    Optional<Empleado> findById(int id) throws Exception;
    List<Empleado> findAll() throws Exception;
    void update(Empleado e) throws Exception;
    void deleteById(int id) throws Exception;
}