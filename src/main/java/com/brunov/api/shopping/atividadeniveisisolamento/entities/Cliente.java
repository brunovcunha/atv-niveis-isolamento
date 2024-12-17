package com.brunov.api.shopping.atividadeniveisisolamento.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(name = "ClienteID", length = 5)
    private String id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "cargo", length = 30)
    private String cargo;

    @Column(name = "endereco", length = 60)
    private String endereco;

    @Column(name = "cidade", length = 15)
    private String cidade;

    @Column(name = "cep", length = 10)
    private String cep;

    @Column(name = "pais", length = 15)
    private String pais;

    @Column(name = "telefone", length = 24)
    private String telefone;

    @Column(name = "Fax", length = 24)
    private String fax;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

}