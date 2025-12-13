package com.datavault;
import java.util.List;
import java.util.Optional;
import com.model.Prestamo;

public interface PrestamoDv {
    public Prestamo saveP(Prestamo prestamo) throws Exception;
    public Optional<Prestamo> findPById(int id) throws Exception;
    public List<Prestamo> findPAll() throws Exception;
    public List<Prestamo> findPByPrestamoId(int id) throws Exception;
    public void updateP(Prestamo prestamo) throws Exception;
    public void deletePById(int id) throws Exception;
}