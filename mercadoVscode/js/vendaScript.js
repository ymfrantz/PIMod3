$(document).ready(function () {


    function listarProdutos() {
        $.ajax({
            url: 'http://localhost:8080/produto/listar',
            method: 'GET',
            success: function (data) {
                $('#produtosTableBody').empty();

                for (let i = 0; i < data.length; i++) {
                    let produto = data[i];
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
                            let quantidade = $(this).closest('tr').find('td:nth-child(4)').text();
                            let validade = $(this).closest('tr').find('td:nth-child(5)').text();
                            let valor = $(this).closest('tr').find('td:nth-child(6)').text();
                            
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
                                                })
                                        )
                                );
                            
                            $('#carrinhoTableBody').append(carrinhoRow);
                            
                        });

                    let tr = $('<tr>').attr('data-id', produto.id)
                        .append(id)
                        .append(nome)
                        .append(descricao)
                        .append(quantidade)
                        .append(validade)
                        .append(valor)
                        .append(botaoAdicionar)

                    $('#produtosTableBody').append(tr);
                }

            },
            error: function (xhr, status, error) {
                alert('Erro ao listar o produto: ' + error);
            }
        });
    }


    function buscaPorNome(nome) {
        $.ajax({
            url: 'http://localhost:8080/produto/buscarPorNome/' + nome,
            method: 'GET',
            success: function (data) {
                $('#produtosTableBody').empty();

                for (let i = 0; i < data.length; i++) {
                    let produto = data[i];
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
                            let quantidade = $(this).closest('tr').find('td:nth-child(4)').text();
                            let validade = $(this).closest('tr').find('td:nth-child(5)').text();
                            let valor = $(this).closest('tr').find('td:nth-child(6)').text();
                            
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
                                                })
                                        )
                                );
                            
                            $('#carrinhoTableBody').append(carrinhoRow);
                            
                        });

                    let tr = $('<tr>').attr('data-id', produto.id)
                        .append(id)
                        .append(nome)
                        .append(descricao)
                        .append(quantidade)
                        .append(validade)
                        .append(valor)
                        .append(botaoAdicionar)

                    $('#produtosTableBody').append(tr);
                }
            },
            error: function (xhr, status, error) {
                alert('Erro ao filtrar por produto: ' + error);
            }
        });
    }


    $('#limpaBusca').on('click', function (vent) {
        $('#inputBusca').val('');
        listarProdutos();

    });

    $('#bucarProduto').on('click', function (vent) {
        let nomeBusca = $('#inputBusca').val().trim();
        buscaPorNome(nomeBusca);
    });


    listarProdutos();

});