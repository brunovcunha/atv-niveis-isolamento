package com.brunov.api.shopping.atividadeniveisisolamento.repositories;

import com.brunov.api.shopping.atividadeniveisisolamento.entities.Produto;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM produtos p WHERE p.id = :produtoId")
    Optional<Produto> findByIdWithPessimisticLock(@Param("produtoId") Integer produtoId);

    boolean existsByNome(String nome);

    Produto findProdutoByNome(String nome);
}