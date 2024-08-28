package br.com.empresa.servicodepedidos.core.consumer;

import br.com.empresa.servicodepedidos.core.model.Cliente;
import br.com.empresa.servicodepedidos.core.model.Item;
import br.com.empresa.servicodepedidos.core.model.Pedido;
import br.com.empresa.servicodepedidos.core.model.Produto;
import br.com.empresa.servicodepedidos.core.repository.ClienteRepository;
import br.com.empresa.servicodepedidos.core.repository.ItemRepository;
import br.com.empresa.servicodepedidos.core.repository.PedidoRepository;
import br.com.empresa.servicodepedidos.core.repository.ProdutoRepository;
import br.com.empresa.servicodepedidos.core.dtos.PedidoDTO;
import br.com.empresa.servicodepedidos.core.dtos.ItemDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoConsumer {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;
    private final ObjectMapper objectMapper;

    public PedidoConsumer(PedidoRepository pedidoRepository,
                          ClienteRepository clienteRepository,
                          ProdutoRepository produtoRepository,
                          ItemRepository itemRepository,
                          ObjectMapper objectMapper) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itemRepository = itemRepository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "pedidoQueue")
    @Transactional
    public void consumeMessage(String message) {
        try {
            PedidoDTO pedidoDTO = objectMapper.readValue(message, PedidoDTO.class);

            // Localizar ou criar o cliente
            Optional<Cliente> optionalCliente = clienteRepository.findByCodigoCliente(pedidoDTO.getCodigoCliente());
            Cliente cliente = optionalCliente.orElseGet(() -> {
                Cliente novoCliente = new Cliente();
                novoCliente.setCodigoCliente(pedidoDTO.getCodigoCliente());
                // Defina outras propriedades do cliente conforme necessário
                return clienteRepository.save(novoCliente);
            });

            // Criar o pedido
            Pedido pedido = new Pedido();
            pedido.setCodigoPedido(pedidoDTO.getCodigoPedido());
            pedido.setCliente(cliente);
            pedido.setTotal(0.0);

            // Processar itens
            for (ItemDTO itemDTO : pedidoDTO.getItens()) {
                Optional<Produto> optionalProduto = produtoRepository.findByNome(itemDTO.getProduto());
                Produto produto = optionalProduto.orElseThrow(() ->
                        new RuntimeException("Produto não encontrado: " + itemDTO.getProduto()));

                Item item = new Item();
                item.setProduto(produto);
                item.setQuantidade(itemDTO.getQuantidade());
                item.setPreco(itemDTO.getPreco());
                item.setSubtotal(itemDTO.getQuantidade() * itemDTO.getPreco());
                item.setPedido(pedido);

                pedido.getItens().add(item);
                pedido.setTotal(pedido.getTotal() + item.getSubtotal());

                itemRepository.save(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
