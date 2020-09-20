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
import org.springframework.web.bind.annotation.RestController;

import com.gftbank.dtb.entities.Cliente;
import com.gftbank.dtb.service.ClienteService;
import com.gftbank.dtb.utils.ResourceNotFoundException;

@RestController()
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getClients(){
		return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getClient(@PathVariable Long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(clienteService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<Cliente> saveClient(@RequestBody Cliente cliente){
		return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> updateClient(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException{
		return new ResponseEntity<>(clienteService.update(id, cliente), HttpStatus.OK);
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteclient(@PathVariable Long id) throws ResourceNotFoundException {
		return new ResponseEntity<>(clienteService.delete(id), HttpStatus.OK);
	}
	
}
