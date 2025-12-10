package com.datavault;

import com.model.Pago;
import java.util.List;
import java.util.Optional;

public interface PagoDv {

    Pago save(Pago pago) throws Exception;
    Optional<Pago> findById(int id) throws Exception;
    List<Pago> findAll() throws Exception;
    List<Pago> findByPrestamoId(int prestamoId) throws Exception;
    void update(Pago pago) throws Exception;
    void deleteById(int id) throws Exception;
}
