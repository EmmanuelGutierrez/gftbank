package com.gftbank.dtb.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gftbank.dtb.models.entity.Cuenta;

public interface CuentaDao extends CrudRepository<Cuenta, Long> {

	List<Cuenta> findByClienteId(Long clientId);

	Optional<Cuenta> findByIdAndClienteId(Long cuentaId, Long clienteId);
}
