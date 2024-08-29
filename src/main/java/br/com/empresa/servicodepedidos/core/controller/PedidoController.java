package br.com.empresa.servicodepedidos.core.controller;

import br.com.empresa.servicodepedidos.core.dtos.ClientePedidosCountDTO;
import br.com.empresa.servicodepedidos.core.dtos.ItemDTO;
import br.com.empresa.servicodepedidos.core.dtos.PedidoDTO;
import br.com.empresa.servicodepedidos.core.model.Item;
import br.com.empresa.servicodepedidos.core.model.Pedido;
import br.com.empresa.servicodepedidos.core.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/valor-total/{codigoPedido}")
    public Double obterValorTotal(@PathVariable Integer codigoPedido) {
        return pedidoService.calcularValorTotal(codigoPedido);
    }

    @GetMapping("/cliente/{codigoCliente}")
    public List<PedidoDTO> listarPedidosPorCliente(@PathVariable Integer codigoCliente) {
        List<Pedido> pedidos = pedidoService.listarPedidosPorCliente(codigoCliente);
        List<PedidoDTO> pedidoDTOList = new ArrayList<>();

        // Populando a lista
        for (Pedido pedido : pedidos) {

            List<ItemDTO> itemDTOList = new ArrayList<>();
            for (Item item : pedido.getItens()) {
                ItemDTO itemDTO = new ItemDTO(item.getProduto().getNome(), item.getQuantidade(), item.getPreco());
                itemDTOList.add(itemDTO);
            }

            PedidoDTO pedidoDTO = new PedidoDTO(pedido.getCodigoPedido(), pedido.getCliente().getCodigoCliente(), itemDTOList);
            pedidoDTOList.add(pedidoDTO);
        }

        return pedidoDTOList;
    }
}