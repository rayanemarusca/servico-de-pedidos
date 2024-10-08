package br.com.empresa.servicodepedidos.core.dtos;
import com.fasterxml.jackson.annotation.JsonCreator;

public class ItemDTO {
    private String produto;

    public ItemDTO() {
    }

    public ItemDTO(String produto, int quantidade, double preco) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    private int quantidade;
    private double preco;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
