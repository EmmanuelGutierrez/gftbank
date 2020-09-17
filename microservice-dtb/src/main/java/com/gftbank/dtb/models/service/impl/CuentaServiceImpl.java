package com.gftbank.dtb.models.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gftbank.dtb.models.dao.ClienteDao;
import com.gftbank.dtb.models.dao.CuentaDao;
import com.gftbank.dtb.models.entity.Cuenta;
import com.gftbank.dtb.models.service.CuentaService;
import com.gftbank.dtb.utils.ResourceNotFoundException;

@Service
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	private CuentaDao cuentaDao;

	@Autowired
	private ClienteDao clienteDao;

	@Override
	public List<Cuenta> findAll(Long clienteId) {
		return (List<Cuenta>) cuentaDao.findByClienteId(clienteId);
	}

	@Override
	public Cuenta findById(Long cuentaId, Long clienteId) throws ResourceNotFoundException {
		return cuentaDao.findByIdAndClienteId(cuentaId, clienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada :: " + cuentaId));
	}

	@Override
	public Cuenta save(Long clienteId, Cuenta cuenta) throws ResourceNotFoundException {
		return clienteDao.findById(clienteId).map(cliente -> {
			cuenta.setCliente(cliente);
			return cuentaDao.save(cuenta);
		}).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado :: " + clienteId));
	}

	@Override
	public Cuenta update(Long clienteId, Long cuentaId, Cuenta cuenta) throws ResourceNotFoundException {
		if (!clienteDao.existsById(clienteId)) {
			throw new ResourceNotFoundException("Cliente no encontrado :: " + clienteId);
		}
		return cuentaDao.findById(cuentaId).map(cuentaActual -> {
			cuentaActual.setNumeroCuenta(cuenta.getNumeroCuenta());
			cuentaActual.setSaldo(cuenta.getSaldo());
			cuentaActual.setTipoProducto(cuenta.getTipoProducto());
			return cuentaDao.save(cuentaActual);
		}).orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada :: " + cuentaId));
	}

	@Override
	public Map<String, Boolean> delete(Long clienteId, Long cuentaId) throws ResourceNotFoundException {
		return cuentaDao.findByIdAndClienteId(cuentaId, clienteId).map(cuenta -> {
			cuentaDao.delete(cuenta);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}).orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada :: " + cuentaId));
	}

}
