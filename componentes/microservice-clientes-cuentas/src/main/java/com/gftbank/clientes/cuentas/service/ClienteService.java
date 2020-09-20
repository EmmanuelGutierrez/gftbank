package com.gftbank.clientes.cuentas.service;

import java.util.List;
import java.util.Map;

import com.gftbank.clientes.cuentas.models.Cliente;

public interface ClienteService {

	public List<Cliente> findAll();

	public Cliente findById(Long id);

	public Cliente save(Cliente cliente);

	public Cliente update(Long id, Cliente cliente);

	public Map<String, Boolean> delete(Long id);
}
