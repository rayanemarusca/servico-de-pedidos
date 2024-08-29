package br.com.empresa.servicodepedidos.core.service;

import br.com.empresa.servicodepedidos.core.dtos.ClientePedidosCountDTO;
import br.com.empresa.servicodepedidos.core.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<ClientePedidosCountDTO> quantidadePedidosPorCliente() {
        String sql = "SELECT c.id AS cliente_id, c.nome AS cliente_nome, COUNT(p.id) AS quantidade_pedidos " +
                "FROM Cliente c LEFT JOIN Pedido p ON c.id = p.cliente_id " +
                "GROUP BY c.id, c.nome";
        Query query = entityManager.createNativeQuery(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> resultados = query.getResultList();

        return resultados.stream().map(
                resultado -> new ClientePedidosCountDTO(
                        ((Number) resultado[0]).longValue(),  // cliente_id
                        (String) resultado[1],                // cliente_nome
                        ((Number) resultado[2]).longValue()   // quantidade_pedidos
                )
        ).collect(Collectors.toList());
    }
}