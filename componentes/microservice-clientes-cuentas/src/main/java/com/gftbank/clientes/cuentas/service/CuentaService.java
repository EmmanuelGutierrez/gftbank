package com.gftbank.clientes.cuentas.service;

import java.util.List;
import java.util.Map;

import com.gftbank.clientes.cuentas.models.Cuenta;

public interface CuentaService {

	public List<Cuenta> findAll(Long clienteId);

	public Cuenta findById(Long cuentaId, Long clienteId);

	public Cuenta save(Long clienteId, Cuenta cuenta);

	public Cuenta update(Long clienteId, Long cuentaId, Cuenta cuenta);

	public Map<String, Boolean> delete(Long clienteId, Long cuentaId);

}
