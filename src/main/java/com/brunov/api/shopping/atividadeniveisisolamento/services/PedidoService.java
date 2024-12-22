package com.brunov.api.shopping.atividadeniveisisolamento.services;

import com.brunov.api.shopping.atividadeniveisisolamento.entities.*;
import com.brunov.api.shopping.atividadeniveisisolamento.repositories.ClienteRepository;
import com.brunov.api.shopping.atividadeniveisisolamento.repositories.PedidoRepository;
import com.brunov.api.shopping.atividadeniveisisolamento.repositories.ProdutoRepository;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class PedidoService {

    private  ProdutoRepository produtoRepository;
    private  PedidoRepository pedidoRepository;
    private  ClienteRepository clienteRepository;

    public PedidoService(ClienteRepository clienteRepository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public String criarPedidoSemLocks(PedidoRequest request) {
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (produto.getUnidadesEmEstoque() < request.getQuantidade()) {
            throw new IllegalStateException("Estoque insuficiente");
        }

        processarPedido(produto, request);
        return "Pedido criado com sucesso";
    }

    @Transactional
    public String criarPedidoComLockOtimista(PedidoRequest request) {
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (produto.getUnidadesEmEstoque() < request.getQuantidade()) {
            throw new IllegalStateException("Estoque insuficiente");
        }

        try {
            processarPedido(produto, request);
        } catch (OptimisticLockException e) {
            throw new IllegalStateException("Falha no controle de concorrência");
        }
        return "Pedido criado com sucesso";
    }

    @Transactional
    public String criarPedidoComLockPessimista(PedidoRequest request) {
        Produto produto = produtoRepository.findByIdWithPessimisticLock(request.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (produto.getUnidadesEmEstoque() < request.getQuantidade()) {
            throw new IllegalStateException("Estoque insuficiente");
        }

        processarPedido(produto, request);
        return "Pedido criado com sucesso";
    }

    private void processarPedido(Produto produto, PedidoRequest request) {
        produto.setUnidadesEmEstoque(produto.getUnidadesEmEstoque() - request.getQuantidade());
        produtoRepository.save(produto);

        Pedido pedido = new Pedido();
        Cliente cliente = clienteRepository.findById(request.getClienteId()).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        pedido.setCliente(cliente); // Mock de cliente
        pedido.setDataPedido(LocalDateTime.now());

        DetalhePedido detalhes = new DetalhePedido();
        detalhes.setProduto(produto);
        detalhes.setQuantidade(request.getQuantidade());
        detalhes.setPrecoVenda(produto.getPreco());
        detalhes.setPedido(pedido);
        pedido.setDetalhes(new ArrayList<DetalhePedido>());
        pedido.getDetalhes().add(detalhes);

        pedidoRepository.save(pedido);
    }
}

