package com.brunov.api.shopping.atividadeniveisisolamento.config;

import com.brunov.api.shopping.atividadeniveisisolamento.entities.Cliente;
import com.brunov.api.shopping.atividadeniveisisolamento.entities.Produto;
import com.brunov.api.shopping.atividadeniveisisolamento.repositories.ClienteRepository;
import com.brunov.api.shopping.atividadeniveisisolamento.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

    @Bean
    public CommandLineRunner dataLoader(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        return args -> {
            // Verificar e inserir cliente 1
            if (!clienteRepository.existsByNome("João Silva")) {
                Cliente cliente1 = new Cliente();
                cliente1.setNome("João Silva");
                cliente1.setCargo("Cliente");
                cliente1.setEndereco("Rua Exemplo, 123");
                cliente1.setCidade("São Paulo");
                cliente1.setCep("01000-000");
                cliente1.setPais("Brasil");
                cliente1.setTelefone("(11) 1234-5678");
                cliente1.setFax("(11) 9876-5432");
                clienteRepository.save(cliente1);
                System.out.println("Cliente João Silva inserido com sucesso!");
            }

            // Verificar e inserir cliente 2
            if (!clienteRepository.existsByNome("Maria Oliveira")) {
                Cliente cliente2 = new Cliente();
                cliente2.setNome("Maria Oliveira");
                cliente2.setCargo("Cliente");
                cliente2.setEndereco("Avenida Central, 456");
                cliente2.setCidade("Rio de Janeiro");
                cliente2.setCep("20000-000");
                cliente2.setPais("Brasil");
                cliente2.setTelefone("(21) 2345-6789");
                cliente2.setFax("(21) 8765-4321");
                clienteRepository.save(cliente2);
                System.out.println("Cliente Maria Oliveira inserido com sucesso!");
            }

            // Verificar e inserir cliente 3
            if (!clienteRepository.existsByNome("Carlos Souza")) {
                Cliente cliente3 = new Cliente();
                cliente3.setNome("Carlos Souza");
                cliente3.setCargo("Cliente");
                cliente3.setEndereco("Praça da Liberdade, 789");
                cliente3.setCidade("Belo Horizonte");
                cliente3.setCep("30000-000");
                cliente3.setPais("Brasil");
                cliente3.setTelefone("(31) 3456-7890");
                cliente3.setFax("(31) 7654-3210");
                clienteRepository.save(cliente3);
                System.out.println("Cliente Carlos Souza inserido com sucesso!");
            }

            // Verificar e inserir produto
            if (!produtoRepository.existsByNome("PlayStation 5")) {
                Produto produto = new Produto();
                produto.setNome("PlayStation 5");
                produto.setPreco(1999.99);
                produto.setUnidadesEmEstoque(1000);
                produto.setImagem("ps5_imagem_url");
                produtoRepository.save(produto);
                System.out.println("Produto PlayStation 5 inserido com sucesso!");
            }
        };
    }
}

