

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

                    let tr = $('<tr>').attr('data-id', produto.id)
                        .append(id)
                        .append(nome)
                        .append(descricao)
                        .append(quantidade)
                        .append(validade)
                        .append(valor)

                    $('#produtosTableBody').append(tr);
                }

            },
            error: function (xhr, status, error) {
                alert('Erro ao listar o produto: ' + error);
            }
        });
    }

    function criaProduto(produto) {
        $.ajax({
            url: 'http://localhost:8080/produto/adicionar',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(produto),
            success: function (data) {
                $('#nome').val('')
                $('#descricao').val('')
                $('#preco').val('')
                $('#quantidade').val('')
                $('#validade').val('')
                listarProdutos();
                alert('Produto cadastrado com sucesso!');
                form[0].reset();
                form.removeClass('was-validated');
            },
            error: function (xhr, status, error) {
                alert('Erro ao adicionar o produto: ' + error);
            }
        });

    }

    function buscaPorNome(nome){
        $.ajax({
            url: 'http://localhost:8080/produto/buscarPorNome/'+nome,
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

                    let tr = $('<tr>').attr('data-id', produto.id)
                        .append(id)
                        .append(nome)
                        .append(descricao)
                        .append(quantidade)
                        .append(validade)
                        .append(valor)

                    $('#produtosTableBody').append(tr);
                }
            },
            error: function (xhr, status, error) {
                alert('Erro ao filtrar por produto: ' + error);
            }
        });
    }


    $('#limpaBusca').on('click', function (vent){  
        $('#inputBusca').val('');
        listarProdutos();

    });

    $('#bucarProduto').on('click', function (vent){ 
        let nomeBusca = $('#inputBusca').val().trim(); 
        buscaPorNome(nomeBusca); 
    });

    $('#cadastrar').on('click', function (event) {
        event.preventDefault();

        let nome = $('#nome').val().trim();
        let descricao = $('#descricao').val().trim();
        let valor = $('#preco').val().trim();
        let quantidade = $('#quantidade').val().trim();
        let validade = $('#validade').val().trim();

        if (!nome || !descricao || !preco || !quantidade || !validade) {
            alert("Todos os campos são obrigatórios.");
            return;
        }

        let produto = {
            nome: nome,
            descricao: descricao,
            valor: parseFloat(valor),
            quantidade: parseInt(quantidade),
            validade: validade
        };

        criaProduto(produto);
    });


    listarProdutos();

});