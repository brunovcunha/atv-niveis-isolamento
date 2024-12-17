package com.brunov.api.shopping.atividadeniveisisolamento.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
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

    @Column(name = "preco", precision = 10, scale = 2)
    private Double preco;

    @Column(name = "UnidadesEmEstoque")
    private Short unidadesEmEstoque;

    @Column(name = "Imagem", length = 100)
    private String imagem;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<DetalhePedido> detalhesPedido;


}
