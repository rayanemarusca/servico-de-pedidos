package br.com.empresa.servicodepedidos.core.repository;

import br.com.empresa.servicodepedidos.core.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
