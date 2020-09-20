package com.gftbank.dtb;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gftbank.dtb.controllers.CuentaController;
import com.gftbank.dtb.entities.Cuenta;
import com.gftbank.dtb.enums.TipoProducto;
import com.gftbank.dtb.service.impl.CuentaServiceImpl;

@WebMvcTest(controllers = CuentaController.class)
public class MicroserviceDtbApplicationCuentasTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CuentaServiceImpl cuentaService;

	private ObjectMapper mapper = new ObjectMapper();

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
