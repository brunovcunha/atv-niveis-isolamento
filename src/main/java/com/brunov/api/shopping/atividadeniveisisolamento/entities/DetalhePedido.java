package com.brunov.api.shopping.atividadeniveisisolamento.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalhes_pedido")
public class DetalhePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PedidoID", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "ProdutoID", nullable = false)
    private Produto produto;

    @Column(name = "precoVenda", precision = 10, scale = 2)
    private Double precoVenda;

    @Column(name = "quantidade")
    private Short quantidade;

    @Column(name = "desconto", precision = 10, scale = 2)
    private Double desconto;

}
