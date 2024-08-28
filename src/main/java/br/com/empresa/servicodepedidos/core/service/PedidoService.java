package br.com.empresa.servicodepedidos.core.service;

import br.com.empresa.servicodepedidos.core.model.Cliente;
import br.com.empresa.servicodepedidos.core.model.Pedido;
import br.com.empresa.servicodepedidos.core.repository.ClienteRepository;
import br.com.empresa.servicodepedidos.core.repository.PedidoRepository;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Double calcularValorTotal(Integer codigoPedido) {
        Pedido pedido = pedidoRepository.findByCodigoPedido(codigoPedido);
        if (pedido == null) {
            throw new RuntimeException("Pedido não encontrado");
        }
        return pedido.getItens().stream()
                .mapToDouble(item -> item.getPreco() * item.getQuantidade())
                .sum();
    }
}
