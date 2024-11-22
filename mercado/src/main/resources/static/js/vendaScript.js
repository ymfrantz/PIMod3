$(document).ready(function () {
    let carrinho = []; // Lista que armazenará os itens do carrinho
    let valorTotal = 0; // Valor total da venda

    // Função para renderizar os produtos na tabela
    function renderizarProdutos(data) {
        $('#produtosTableBody').empty();
        
        data.forEach(produto => {
            let id = $('<td>').text(produto.id);
            let nome = $('<td>').text(produto.nome);
            let descricao = $('<td>').text(produto.descricao);
            let quantidade = $('<td>').text(produto.quantidade);
            let validade = $('<td>').text(produto.validade);
            let valor = $('<td>').text(produto.valor);
            
            let botaoAdicionar = $('<button>').addClass('btn btn-success w-100 h-100 p-100 m-0 border-0 rounded-0')
                .text('Adicionar').click(function () {
                    let id = $(this).closest('tr').attr('data-id');
                    let nome = $(this).closest('tr').find('td:nth-child(2)').text();
                    let descricao = $(this).closest('tr').find('td:nth-child(3)').text();
                    let valor = parseFloat($(this).closest('tr').find('td:nth-child(5)').text());
                    let validade = $(this).closest('tr').find('td:nth-child(6)').text();
                    let estoque = parseInt($(this).closest('tr').find('td:nth-child(4)').text());

                    let rowCarrinho = $('#carrinhoTableBody tr[data-id="' + id + '"]');
                    if (rowCarrinho.length > 0) {
                        let quantidadeAtual = parseInt(rowCarrinho.find('td:nth-child(4)').text());
                        if (quantidadeAtual + 1 > estoque) {
                            alert('Quantidade excede o estoque disponível!');
                        } else {
                            rowCarrinho.find('td:nth-child(4)').text(quantidadeAtual + 1);
                            atualizarValorTotal();
                        }
                    } else {
                        if (estoque < 1) {
                            alert('Produto sem estoque disponível!');
                        } else {
                            let carrinhoRow = $('<tr>')
                                .attr('data-id', id)
                                .append($('<td>').text(id))
                                .append($('<td>').text(nome))
                                .append($('<td>').text(descricao))
                                .append($('<td>').text(1))
                                .append($('<td>').text(valor))
                                .append($('<td>').text(validade))
                                .append(
                                    $('<td>')
                                        .append(
                                            $('<button>')
                                                .addClass('btn btn-danger')
                                                .text('Remover')
                                                .click(function () {
                                                    $(this).closest('tr').remove();
                                                    atualizarValorTotal();
                                                })
                                        )
                                );
                            $('#carrinhoTableBody').append(carrinhoRow);
                            carrinho.push({ id, nome, descricao, quantidade: 1, valor: valor });
                        }
                    }
                    atualizarValorTotal();
                });

            let tr = $('<tr>').attr('data-id', produto.id)
                .append(id)
                .append(nome)
                .append(descricao)
                .append(quantidade)
                .append(valor)
                .append(validade)
                .append(botaoAdicionar);

            $('#produtosTableBody').append(tr);
        });
    }

    // Função para calcular o valor total do carrinho
    function atualizarValorTotal() {
        valorTotal = 0;
        $('#carrinhoTableBody tr').each(function () {
            let quantidade = parseInt($(this).find('td:nth-child(4)').text());
            let valor = parseFloat($(this).find('td:nth-child(5)').text());
            if (quantidade > 0 && valor > 0) {
                valorTotal += quantidade * valor;
            }
        });
        $('#valorTotal').text(valorTotal.toFixed(2));
    }

    // Função para listar os produtos
    function listarProdutos() {
        $.ajax({
            url: 'http://localhost:8080/produto/listar',
            method: 'GET',
            success: function (data) {
                renderizarProdutos(data);
            },
            error: function (xhr, status, error) {
                alert('Erro ao listar o produto: ' + error);
            }
        });
    }

    // Função de busca por nome
    function buscaPorNome(nome) {
        if (!nome.trim()) {
            listarProdutos();  // Se a busca estiver vazia, carrega todos os produtos
            return;
        }
        
        $.ajax({
            url: 'http://localhost:8080/produto/buscarPorNome/' + nome,
            method: 'GET',
            success: function (data) {
                renderizarProdutos(data);
            },
            error: function (xhr, status, error) {
                alert('Erro ao filtrar por produto: ' + error);
            }
        });
    }

    // Finalizar a venda
    $('#botaoFinalizar').on('click', function () {
        if (carrinho.length === 0) {
            alert('Carrinho vazio. Adicione produtos antes de finalizar.');
            return;
        }

        let dataVenda = new Date().toISOString();

        let venda = {
            dataVenda: dataVenda,
            valorTotal: valorTotal,
            produtos: carrinho.map(item => ({
                id: item.id,
                quantidade: item.quantidade
            }))
        };

        $.ajax({
            url: 'http://localhost:8080/venda/adicionar',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(venda),
            success: function (response) {
                alert('Venda realizada com sucesso!');
                
                $('#carrinhoTableBody').empty();
                $('#valorTotal').text('0.00');
                carrinho = [];
                location.reload();
            },
            error: function (xhr, status, error) {
                alert('Erro ao realizar a venda: ' + xhr.responseText);
            }
        });
    });

    // Limpar e buscar
    $('#limpaBusca').on('click', function (event) {
        $('#inputBusca').val('');
        listarProdutos();
    });

    $('#bucarProduto').on('click', function (event) {
        let nomeBusca = $('#inputBusca').val().trim();
        buscaPorNome(nomeBusca);
    });

    $('#botaoCancelar').on('click', function (event) {
        let tbody = document.querySelector('#carrinhoTableBody');
        while (tbody.rows.length > 1) {
            tbody.deleteRow(1);
        }
        atualizarValorTotal();
    });

    listarProdutos();
});
