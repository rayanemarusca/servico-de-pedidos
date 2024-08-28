package br.com.empresa.servicodepedidos.core.controller;


import br.com.empresa.servicodepedidos.core.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/quantidade-pedidos")
    public List<Map<String, Object>> obterQuantidadePedidosPorCliente() {
        return relatorioService.quantidadePedidosPorCliente();
    }
}