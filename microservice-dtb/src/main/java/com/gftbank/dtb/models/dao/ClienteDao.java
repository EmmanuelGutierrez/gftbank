package com.gftbank.dtb.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.gftbank.dtb.models.entity.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Long> {

}
