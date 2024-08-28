package br.com.empresa.servicodepedidos.core.repository;

import br.com.empresa.servicodepedidos.core.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCodigoCliente(Long codigoCliente);
}
