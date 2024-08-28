package br.com.empresa.servicodepedidos.core.service;

import br.com.empresa.servicodepedidos.core.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> quantidadePedidosPorCliente() {
        String sql = "SELECT c.id AS clienteId, c.nome AS clienteNome, COUNT(p.id) AS quantidadePedidos " +
                "FROM Cliente c LEFT JOIN Pedido p ON c.id = p.cliente_id " +
                "GROUP BY c.id, c.nome";
        Query query = entityManager.createNativeQuery(sql, "ClientePedidosCountMapping");
        return query.getResultList();
    }
}