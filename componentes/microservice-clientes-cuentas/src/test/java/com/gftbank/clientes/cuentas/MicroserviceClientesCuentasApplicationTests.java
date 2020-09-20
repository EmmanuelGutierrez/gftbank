package com.gftbank.clientes.cuentas;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gftbank.clientes.cuentas.controllers.ClienteCuentasController;
import com.gftbank.clientes.cuentas.enums.Genero;
import com.gftbank.clientes.cuentas.enums.TipoProducto;
import com.gftbank.clientes.cuentas.models.Cliente;
import com.gftbank.clientes.cuentas.models.Cuenta;
import com.gftbank.clientes.cuentas.service.impl.ClienteServiceImpl;
import com.gftbank.clientes.cuentas.service.impl.CuentaServiceImpl;

@WebMvcTest(controllers = ClienteCuentasController.class)
class MicroserviceClientesCuentasApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClienteServiceImpl clienteService;

	@MockBean
	private CuentaServiceImpl cuentaService;

	private ObjectMapper mapper = new ObjectMapper();

	List<Cliente> clientes;

	Cliente cliente;

	Cliente nuevoCliente;

	List<Cuenta> cuentas;

	Cuenta cuenta;

	Cuenta nuevaCuenta;

	@BeforeEach
	void setUp() {
		this.cuentas = new ArrayList<>();
		this.cuenta = new Cuenta();
		this.cuenta.setId(1L);
		this.cuenta.setNumeroCuenta("99999999990");
		this.cuenta.setSaldo(5000d);
		this.cuenta.setTipoProducto(TipoProducto.PRODUCTOA);
		cuentas.add(cuenta);
		this.cuenta = new Cuenta();
		this.cuenta.setId(2L);
		this.cuenta.setNumeroCuenta("99999999991");
		this.cuenta.setSaldo(5000d);
		this.cuenta.setTipoProducto(TipoProducto.PRODUCTOB);
		cuentas.add(cuenta);
		this.nuevaCuenta = new Cuenta();
		this.nuevaCuenta.setId(2L);
		this.nuevaCuenta.setNumeroCuenta("99999999991");
		this.nuevaCuenta.setSaldo(5000d);
		this.nuevaCuenta.setTipoProducto(TipoProducto.PRODUCTOA);
		this.clientes = new ArrayList<>();
		this.cliente = new Cliente();
		this.cliente.setId(1L);
		this.cliente.setApellidos("Perez Gonzalez");
		this.cliente.setFechaNacimiento(
				Date.from(LocalDate.of(1980, 10, 11).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		this.cliente.setNombre("Silvia");
		this.cliente.setSexo(Genero.F);
		this.clientes.add(cliente);
		clientes.add(cliente);
		this.cliente = new Cliente();
		this.cliente.setId(2L);
		this.cliente.setApellidos("Solorzano Solis");
		this.cliente.setFechaNacimiento(
				Date.from(LocalDate.of(1985, 11, 23).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		this.cliente.setNombre("Mauricio");
		this.cliente.setSexo(Genero.M);
		clientes.add(cliente);
		this.nuevoCliente = new Cliente();
		this.nuevoCliente.setId(null);
		this.nuevoCliente.setApellidos("Solorzano Solis");
		this.nuevoCliente.setFechaNacimiento(
				Date.from(LocalDate.of(1985, 11, 23).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		this.nuevoCliente.setNombre("Mauricio");
		this.nuevoCliente.setSexo(Genero.M);
	}

	@Test
	public void getClientes() throws Exception {
		when(clienteService.findAll()).thenReturn(clientes);
		mockMvc.perform(get("/clientes")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(clientes.size())));
	}

	@Test
	public void getCliente() throws Exception {
		Long clienteId = 2L;
		when(clienteService.findById(clienteId)).thenReturn(this.clientes.get(1));
		mockMvc.perform(get("/clientes/{id}", clienteId.toString())).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(this.clientes.get(1).getId()), Long.class));
	}

	@Test
	public void postCliente() throws Exception {
		when(clienteService.save(ArgumentMatchers.any())).thenReturn(this.clientes.get(0));
		mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(this.mapper.writeValueAsString(this.nuevoCliente)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void putCliente() throws Exception {
		Long clienteId = 1L;
		when(clienteService.update(clienteId, this.clientes.get(0))).thenReturn(this.clientes.get(0));
		mockMvc.perform(put("/clientes/{id}", clienteId.toString()).contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(this.mapper.writeValueAsString(this.nuevoCliente))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void deleteCliente() throws Exception {
		Long clienteId = 1L;
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		when(clienteService.delete(clienteId)).thenReturn(response);
		mockMvc.perform(delete("/clientes/{id}", clienteId.toString())).andExpect(status().isOk());
	}

	@Test
	public void getCuentas() throws Exception {
		Long clienteId = 1L;
		when(cuentaService.findAll(clienteId)).thenReturn(cuentas);
		mockMvc.perform(get("/clientes/{clienteId}/cuentas", clienteId.toString())).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(cuentas.size())));
	}

	@Test
	public void getCuenta() throws Exception {
		Long clienteId = 1L;
		Long cuentaId = 1L;
		when(cuentaService.findById(cuentaId, clienteId)).thenReturn(this.cuentas.get(1));
		mockMvc.perform(get("/clientes/{clienteId}/cuentas/{cuentaId}", clienteId.toString(), cuentaId.toString()))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(this.cuentas.get(1).getId()), Long.class));
	}

	@Test
	public void postCuenta() throws Exception {
		Long clienteId = 1L;
		when(cuentaService.save(clienteId, this.cuentas.get(0))).thenReturn(this.cuentas.get(0));
		mockMvc.perform(post("/clientes/{clienteId}/cuentas", clienteId.toString())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(this.mapper.writeValueAsString(this.nuevaCuenta)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void putCuenta() throws Exception {
		Long clienteId = 1L;
		Long cuentaId = 1L;
		when(cuentaService.update(clienteId, cuentaId, this.cuentas.get(0))).thenReturn(this.cuentas.get(0));
		mockMvc.perform(put("/clientes/{clienteId}/cuentas/{cuentaId}", clienteId.toString(), cuentaId.toString())
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(this.mapper.writeValueAsString(this.nuevaCuenta)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteCuenta() throws Exception {
		Long clienteId = 1L;
		Long cuentaId = 1L;
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		when(cuentaService.delete(clienteId, cuentaId)).thenReturn(response);
		mockMvc.perform(delete("/clientes/{clienteId}/cuentas/{cuentaId}", clienteId.toString(), cuentaId.toString()))
				.andExpect(status().isOk());
	}

}
