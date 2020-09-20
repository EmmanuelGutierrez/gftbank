package com.gftbank.clientes.cuentas.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.gftbank.clientes.cuentas.models.Cliente;
import com.gftbank.clientes.cuentas.models.Cuenta;
import com.gftbank.clientes.cuentas.service.ClienteService;
import com.gftbank.clientes.cuentas.service.CuentaService;

@RestController
public class ClienteCuentasController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CuentaService cuentaService;
	
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getClients() {
		return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getClient(@PathVariable Long id) {
		return new ResponseEntity<>(clienteService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/clientes")
	public ResponseEntity<Cliente> saveClient(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> updateClient(@PathVariable Long id, @RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.update(id, cliente), HttpStatus.OK);
	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteclient(@PathVariable Long id) {
		return new ResponseEntity<>(clienteService.delete(id), HttpStatus.OK);
	}

	@GetMapping("/clientes/{clienteId}/cuentas")
	public ResponseEntity<List<Cuenta>> getClients(@PathVariable Long clienteId) {
		return new ResponseEntity<>(cuentaService.findAll(clienteId), HttpStatus.OK);
	}

	@GetMapping("/clientes/{clienteId}/cuentas/{cuentaId}")
	public ResponseEntity<Cuenta> getClient(@PathVariable Long cuentaId, @PathVariable Long clienteId) {
		return new ResponseEntity<>(cuentaService.findById(cuentaId, clienteId), HttpStatus.OK);
	}

	@PostMapping("/clientes/{clienteId}/cuentas")
	public ResponseEntity<Cuenta> saveClient(@PathVariable Long clienteId, @RequestBody Cuenta cuenta) {
		return new ResponseEntity<>(cuentaService.save(clienteId, cuenta), HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{clienteId}/cuentas/{cuentaId}")
	public ResponseEntity<Cuenta> updateClient(@PathVariable Long clienteId, @PathVariable Long cuentaId,
			@RequestBody Cuenta cuenta) {
		return new ResponseEntity<>(cuentaService.update(clienteId, cuentaId, cuenta), HttpStatus.OK);
	}

	@DeleteMapping("/clientes/{clienteId}/cuentas/{cuentaId}")
	public ResponseEntity<Map<String, Boolean>> deleteclient(@PathVariable Long clienteId,
			@PathVariable Long cuentaId) {
		return new ResponseEntity<>(cuentaService.delete(clienteId, cuentaId), HttpStatus.OK);
	}

}
