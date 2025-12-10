package com.datavault;

import com.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteDv {
    Cliente save(Cliente e) throws Exception;
    Optional<Cliente> findById(int id) throws Exception;
    List<Cliente> findAll() throws Exception;
    void update(Cliente e) throws Exception;
    void deleteById(int id) throws Exception;
}
