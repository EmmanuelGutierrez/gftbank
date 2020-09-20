package com.gftbank.dtb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gftbank.dtb.entities.Cuenta;

public interface CuentaDao extends CrudRepository<Cuenta, Long> {

	List<Cuenta> findByClienteId(Long clientId);

	Optional<Cuenta> findByIdAndClienteId(Long cuentaId, Long clienteId);
}
