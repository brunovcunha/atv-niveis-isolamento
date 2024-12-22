const BASE_URL = 'http://localhost:8080';

function exibirLogs(texto) {
    const logsDiv = document.getElementById('logs');
    logsDiv.textContent += texto + '\n';
    logsDiv.scrollTop = logsDiv.scrollHeight;
}

async function fazerRequisicoes(endpoint, numeroDeRequisicoes, quantidadeMaxima) {
    const pedidos = [];
    const resultados = { sucesso: 0, erro: 0, statusCodes: [] };

    for (let i = 0; i < numeroDeRequisicoes; i++) {
        const pedido = {
            clienteId: Math.floor(Math.random() * 10) + 1,
            produtoId: 1,
            quantidade: Math.floor(Math.random() * quantidadeMaxima) + 1
        };

        pedidos.push(axios.post(`${BASE_URL}${endpoint}`, pedido)
            .then(response => {
                resultados.sucesso++;
                resultados.statusCodes.push(response.status);
            })
            .catch(error => {
                resultados.erro++;
                if (error.response) {
                    resultados.statusCodes.push(error.response.status);
                } else {
                    resultados.statusCodes.push('erro');
                }
            }));
    }

    await Promise.all(pedidos);
    return resultados;
}

async function medirDesempenho(endpoint, numeroDeRequisicoes, quantidadeMaxima) {
    const inicio = Date.now();
    const resultados = await fazerRequisicoes(endpoint, numeroDeRequisicoes, quantidadeMaxima);
    const fim = Date.now();

    const tempoTotal = fim - inicio;
    const tempoMedio = tempoTotal / numeroDeRequisicoes;

    return { tempoMedio, tempoTotal, sucesso: resultados.sucesso, erro: resultados.erro, statusCodes: resultados.statusCodes };
}

async function testarSemLocks(numeroDeRequisicoes) {
    const quantidadeMaxima = 5;

    exibirLogs(`Testando Sem Locks com ${numeroDeRequisicoes} requisições...\n`);
    const semLocks = await medirDesempenho("/pedido/novo", numeroDeRequisicoes, quantidadeMaxima);

    exibirLogs(`Sem Locks - Tempo Total: ${semLocks.tempoTotal} ms`);
    exibirLogs(`Sem Locks - Tempo Médio por Requisição: ${semLocks.tempoMedio} ms`);
    exibirLogs(`Sem Locks - Requisições com Sucesso: ${semLocks.sucesso}`);
    exibirLogs(`Sem Locks - Requisições com Erro: ${semLocks.erro}`);
    exibirLogs(`Sem Locks - Status de Códigos de Erro 409: ${semLocks.statusCodes.filter(code => code === 409).length}\n`);
}

async function testarLockOtimista(numeroDeRequisicoes) {
    const quantidadeMaxima = 5;

    exibirLogs(`Testando com Lock Otimista com ${numeroDeRequisicoes} requisições...\n`);
    const otimista = await medirDesempenho("/pedido_otimista/novo", numeroDeRequisicoes, quantidadeMaxima);

    exibirLogs(`Lock Otimista - Tempo Total: ${otimista.tempoTotal} ms`);
    exibirLogs(`Lock Otimista - Tempo Médio por Requisição: ${otimista.tempoMedio} ms`);
    exibirLogs(`Lock Otimista - Requisições com Sucesso: ${otimista.sucesso}`);
    exibirLogs(`Lock Otimista - Requisições com Erro: ${otimista.erro}`);
    exibirLogs(`Lock Otimista - Status de Códigos de Erro 409: ${otimista.statusCodes.filter(code => code === 409).length}\n`);
}

async function testarLockPessimista(numeroDeRequisicoes) {
    const quantidadeMaxima = 5;

    exibirLogs(`Testando com Lock Pessimista com ${numeroDeRequisicoes} requisições...\n`);
    const pessimista = await medirDesempenho("/pedido_pessimista/novo", numeroDeRequisicoes, quantidadeMaxima);

    exibirLogs(`Lock Pessimista - Tempo Total: ${pessimista.tempoTotal} ms`);
    exibirLogs(`Lock Pessimista - Tempo Médio por Requisição: ${pessimista.tempoMedio} ms`);
    exibirLogs(`Lock Pessimista - Requisições com Sucesso: ${pessimista.sucesso}`);
    exibirLogs(`Lock Pessimista - Requisições com Erro: ${pessimista.erro}`);
    exibirLogs(`Lock Pessimista - Status de Códigos de Erro 409: ${pessimista.statusCodes.filter(code => code === 409).length}\n`);
}
