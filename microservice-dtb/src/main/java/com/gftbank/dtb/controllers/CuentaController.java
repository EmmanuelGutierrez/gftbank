package com.gftbank.dtb.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gftbank.dtb.models.entity.Cuenta;
import com.gftbank.dtb.models.service.CuentaService;
import com.gftbank.dtb.utils.ResourceNotFoundException;

@RestController()
@RequestMapping("/api/v1")
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;

	@GetMapping("/clientes/{clienteId}/cuentas")
	public ResponseEntity<List<Cuenta>> getClients(@PathVariable Long clienteId) {
		return new ResponseEntity<>(cuentaService.findAll(clienteId), HttpStatus.OK);
	}

	@GetMapping("/clientes/{clienteId}/cuentas/{cuentaId}")
	public ResponseEntity<Cuenta> getClient(@PathVariable Long cuentaId, @PathVariable Long clienteId) throws ResourceNotFoundException {
		return new ResponseEntity<>(cuentaService.findById(cuentaId, clienteId), HttpStatus.OK);
	}

	@PostMapping("/clientes/{clienteId}/cuentas")
	public ResponseEntity<Cuenta> saveClient(@PathVariable Long clienteId, @RequestBody Cuenta cuenta) throws ResourceNotFoundException {
		return new ResponseEntity<>(cuentaService.save(clienteId, cuenta), HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{clienteId}/cuentas/{cuentaId}")
	public ResponseEntity<Cuenta> updateClient(@PathVariable Long clienteId, @PathVariable Long cuentaId, @RequestBody Cuenta cuenta)
			throws ResourceNotFoundException {
		return new ResponseEntity<>(cuentaService.update(clienteId, cuentaId, cuenta), HttpStatus.OK);
	}

	@DeleteMapping("/clientes/{clienteId}/cuentas/{cuentaId}")
	public ResponseEntity<Map<String, Boolean>> deleteclient(@PathVariable Long clienteId, @PathVariable Long cuentaId) throws ResourceNotFoundException {
		return new ResponseEntity<>(cuentaService.delete(clienteId, cuentaId), HttpStatus.OK);
	}

}
