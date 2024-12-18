package org.ti.inte.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.ti.inte.model.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {
}