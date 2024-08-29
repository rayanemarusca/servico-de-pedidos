package br.com.empresa.servicodepedidos.core.dtos;

import java.util.List;

public class PedidoDTO {
    private Long codigoPedido;
    private Long codigoCliente;
    private List<ItemDTO> itens;

    public PedidoDTO(Long codigoPedido, Long codigoCliente, List<ItemDTO> itens) {
        this.codigoPedido = codigoPedido;
        this.codigoCliente = codigoCliente;
        this.itens = itens;
    }

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public List<ItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemDTO> itens) {
        this.itens = itens;
    }

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
}