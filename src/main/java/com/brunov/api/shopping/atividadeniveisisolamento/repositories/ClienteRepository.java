package com.brunov.api.shopping.atividadeniveisisolamento.repositories;

import com.brunov.api.shopping.atividadeniveisisolamento.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByNome(String nome);
}