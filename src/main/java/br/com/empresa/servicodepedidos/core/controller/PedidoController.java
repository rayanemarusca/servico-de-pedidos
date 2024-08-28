package br.com.empresa.servicodepedidos.core.controller;

import br.com.empresa.servicodepedidos.core.model.Pedido;
import br.com.empresa.servicodepedidos.core.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/valor-total/{codigoPedido}")
    public Double obterValorTotal(@PathVariable Integer codigoPedido) {
        return pedidoService.calcularValorTotal(codigoPedido);
    }

   // @GetMapping("/cliente/{codigoCliente}")
   // public List<Pedido> listarPedidosPorCliente(@PathVariable Long codigoCliente) {
   //     return pedidoService.listarPedidosPorCliente(codigoCliente);
   // }
}