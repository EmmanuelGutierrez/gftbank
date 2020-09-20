package com.gftbank.clientes.cuentas.clients;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gftbank.clientes.cuentas.models.Cliente;
import com.gftbank.clientes.cuentas.models.Cuenta;

@FeignClient(name = "microservice-dtb")
public interface ClienteCuentaRestClient {

	@GetMapping("/clientes")
	public List<Cliente> findAll();

	@GetMapping("/clientes/{id}")
	public Cliente findById(@PathVariable("id") Long id);

	@PostMapping("/clientes")
	public Cliente save(@RequestBody Cliente cliente);

	@PutMapping("/clientes/{id}")
	public Cliente update(@PathVariable("id") Long id, @RequestBody Cliente cliente);

	@DeleteMapping("/clientes/{id}")
	public Map<String, Boolean> delete(@PathVariable("id") Long id);

	@GetMapping("/clientes/{clienteId}/cuentas")
	public List<Cuenta> findAll(@PathVariable("clienteId") Long clienteId);

	@GetMapping("/clientes/{clienteId}/cuentas/{cuentaId}")
	public Cuenta findById(@PathVariable("cuentaId") Long cuentaId, @PathVariable("clienteId") Long clienteId);

	@PostMapping("/clientes/{clienteId}/cuentas")
	public Cuenta save(@PathVariable("clienteId") Long clienteId, @RequestBody Cuenta cuenta);

	@PutMapping("/clientes/{clienteId}/cuentas/{cuentaId}")
	public Cuenta update(@PathVariable("clienteId") Long clienteId, @PathVariable("cuentaId") Long cuentaId, @RequestBody Cuenta cuenta);

	@DeleteMapping("/clientes/{clienteId}/cuentas/{cuentaId}")
	public Map<String, Boolean> delete(@PathVariable("clienteId") Long clienteId, @PathVariable("cuentaId") Long cuentaId);

}
