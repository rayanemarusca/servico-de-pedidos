package br.com.empresa.servicodepedidos.core.repository;

import br.com.empresa.servicodepedidos.core.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Pedido findByCodigoPedido(Integer codigoPedido);
    List<Pedido> findByClienteId(Integer codigoCliente);
}
