package org.datacoins.kinvana.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.datacoins.kinvana.persistence.entity.Cliente;

public interface ClienteCrud extends JpaRepository<Cliente, Integer> {


}
