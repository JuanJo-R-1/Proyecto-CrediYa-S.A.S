package com.service;

import java.util.List;
import java.util.Optional;

import com.datavault.ClienteDv;
import com.datavault.jdbc.ClienteJdbcDv;
import com.model.Cliente;

public class ClienteService {
    private final ClienteDv clienteDv;

    public ClienteService() throws Exception{
        this.clienteDv = new ClienteJdbcDv();
    }

    public Cliente  crearCliente(Cliente c) throws Exception{
        if (c.getNombre() == null || c.getNombre().isEmpty()) {
            throw new Exception("El nombre no puede estar vacio");
        }
        if (c.getDocumento() == null || c.getDocumento().isEmpty())
            throw new Exception("El documento no puede estar vacio"); {
            
        }
        return clienteDv.save(c);
    }

    public Optional<Cliente> buscarPorId(int id) throws Exception {
        return clienteDv.findById(id);
    }

    public List<Cliente> listarClientes() throws Exception {
        return clienteDv.findAll();
    }

     public void actualizarCliente(Cliente c) throws Exception {
        if (c.getId() == null) {
            throw new Exception("el id del cliente es obligatorio");
        }
        clienteDv.update(c);
    }
    public void eliminarCliente(int id) throws Exception{
        clienteDv.findById(id);
    }
}
