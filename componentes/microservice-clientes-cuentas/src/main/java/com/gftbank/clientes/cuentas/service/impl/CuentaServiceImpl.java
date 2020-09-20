package com.gftbank.clientes.cuentas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gftbank.clientes.cuentas.clients.ClienteCuentaRestClient;
import com.gftbank.clientes.cuentas.models.Cuenta;
import com.gftbank.clientes.cuentas.service.CuentaService;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	private ClienteCuentaRestClient clienteCuentaRestClient;
	
	@Override
	public List<Cuenta> findAll(Long clienteId) {
		return clienteCuentaRestClient.findAll(clienteId);
	}

	@Override
	public Cuenta findById(Long cuentaId, Long clienteId) {
		return clienteCuentaRestClient.findById(cuentaId, clienteId);
	}

	@Override
	public Cuenta save(Long clienteId, Cuenta cuenta) {
		return clienteCuentaRestClient.save(clienteId, cuenta);
	}

	@Override
	public Cuenta update(Long clienteId, Long cuentaId, Cuenta cuenta) {
		return clienteCuentaRestClient.update(clienteId, cuentaId, cuenta);
	}

	@Override
	public Map<String, Boolean> delete(Long clienteId, Long cuentaId) {
		return clienteCuentaRestClient.delete(clienteId, cuentaId);
	}

}
