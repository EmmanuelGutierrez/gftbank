package com.gftbank.clientes.cuentas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gftbank.clientes.cuentas.clients.ClienteCuentaRestClient;
import com.gftbank.clientes.cuentas.models.Cliente;
import com.gftbank.clientes.cuentas.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteCuentaRestClient clienteCuentaRestClient; 
	
	@Override
	public List<Cliente> findAll() {
		return clienteCuentaRestClient.findAll();
	}

	@Override
	public Cliente findById(Long id) {
		return clienteCuentaRestClient.findById(id);
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteCuentaRestClient.save(cliente);
	}

	@Override
	public Cliente update(Long id, Cliente cliente) {
		return clienteCuentaRestClient.update(id, cliente);
	}

	@Override
	public Map<String, Boolean> delete(Long id) {
		return clienteCuentaRestClient.delete(id);
	}

}
