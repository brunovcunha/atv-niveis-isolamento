package com.brunov.api.shopping.atividadeniveisisolamento.repositories;

import com.brunov.api.shopping.atividadeniveisisolamento.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}