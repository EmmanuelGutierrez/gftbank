package com.gftbank.dtb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gftbank.dtb.dao.ClienteDao;
import com.gftbank.dtb.entities.Cliente;
import com.gftbank.dtb.service.ClienteService;
import com.gftbank.dtb.utils.ResourceNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDao clienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) throws ResourceNotFoundException {
		return clienteDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado :: " + id));
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public Cliente update(Long id, Cliente cliente) throws ResourceNotFoundException {
		Cliente clienteActual = clienteDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado :: " + id));
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setApellidos(cliente.getApellidos());
		clienteActual.setFechaNacimiento(cliente.getFechaNacimiento());
		clienteActual.setSexo(cliente.getSexo());
		return clienteDao.save(clienteActual);
	}

	@Override
	@Transactional
	public Map<String, Boolean> delete(Long id) throws ResourceNotFoundException {
		Cliente clienteActual = clienteDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado :: " + id));
		clienteDao.delete(clienteActual);
		Map < String, Boolean > response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
		return response;
	}

}
