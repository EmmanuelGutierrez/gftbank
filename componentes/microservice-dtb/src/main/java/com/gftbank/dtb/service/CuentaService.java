package com.gftbank.dtb.service;

import java.util.List;
import java.util.Map;

import com.gftbank.dtb.entities.Cuenta;
import com.gftbank.dtb.utils.ResourceNotFoundException;

public interface CuentaService {

	public List<Cuenta> findAll(Long clienteId);

	public Cuenta findById(Long cuentaId, Long clienteId) throws ResourceNotFoundException;
	
	public Cuenta save(Long clienteId, Cuenta cuenta) throws ResourceNotFoundException;
	
	public Cuenta update(Long clienteId, Long cuentaId, Cuenta cuenta) throws ResourceNotFoundException;
	
	public Map<String, Boolean> delete(Long clienteId, Long cuentaId) throws ResourceNotFoundException;
}
