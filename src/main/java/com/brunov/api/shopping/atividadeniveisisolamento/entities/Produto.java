package com.brunov.api.shopping.atividadeniveisisolamento.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProdutoID")
    private Integer id;

    @Column(name = "ProdutoNome", nullable = false, length = 60)
    private String nome;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "UnidadesEmEstoque")
    private Integer unidadesEmEstoque;

    @Column(name = "Imagem", length = 100)
    private String imagem;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<DetalhePedido> detalhesPedido;

    @Version
    @Column(name = "versao")
    private Integer versao;

    public List<DetalhePedido> getDetalhesPedido() {
        return detalhesPedido;
    }

    public void setDetalhesPedido(List<DetalhePedido> detalhesPedido) {
        this.detalhesPedido = detalhesPedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getUnidadesEmEstoque() {
        return unidadesEmEstoque;
    }

    public void setUnidadesEmEstoque(Integer unidadesEmEstoque) {
        this.unidadesEmEstoque = unidadesEmEstoque;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }
}
