package com.gftbank.dtb.service;

import java.util.List;
import java.util.Map;

import com.gftbank.dtb.entities.Cliente;
import com.gftbank.dtb.utils.ResourceNotFoundException;

public interface ClienteService {

	public List<Cliente> findAll();

	public Cliente findById(Long id) throws ResourceNotFoundException;
	
	public Cliente save(Cliente cliente);
	
	public Cliente update(Long id, Cliente cliente) throws ResourceNotFoundException;
	
	public Map<String, Boolean> delete(Long id) throws ResourceNotFoundException;
}
