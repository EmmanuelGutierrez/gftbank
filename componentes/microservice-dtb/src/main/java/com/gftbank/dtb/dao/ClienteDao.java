package com.gftbank.dtb.dao;

import org.springframework.data.repository.CrudRepository;

import com.gftbank.dtb.entities.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Long> {

}
