package com.brunov.api.shopping.atividadeniveisisolamento.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PedidoID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ClienteID", nullable = false)
    private Cliente cliente;

    @Column(name = "dataPedido")
    private LocalDateTime dataPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetalhePedido> detalhes;

}
