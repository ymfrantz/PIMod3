package senac.pi.mercado.controller;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senac.pi.mercado.model.ProdutoEntity;

import senac.pi.mercado.model.VendaEntity;
import senac.pi.mercado.model.VendaProduto;
import senac.pi.mercado.request.ProdutoVenda;
import senac.pi.mercado.request.VendaRequest;
import senac.pi.mercado.service.ProdutoService;
import senac.pi.mercado.service.VendaProdutoService;
import senac.pi.mercado.service.VendaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private VendaProdutoService vendaProdutoService;

    // Listar todas as vendas
    @GetMapping("/listar")
    public ResponseEntity<List<VendaEntity>> listarTodasVendas() {
        List<VendaEntity> vendas = vendaService.listarVendas();
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    // Buscar venda por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<VendaEntity> buscarVendaPorId(@PathVariable Long id) {
        VendaEntity venda = vendaService.buscarVendaPorId(id);
        return new ResponseEntity<>(venda, HttpStatus.OK);
    }

@PostMapping("/adicionar")
public ResponseEntity<VendaEntity> adicionarVenda(@Valid @RequestBody VendaRequest vendaRequest) {
    // Criando a venda
    VendaEntity novaVenda = new VendaEntity();
    novaVenda.setDataVenda(vendaRequest.getDataVenda());
    novaVenda.setValorTotal(vendaRequest.getValorTotal());

    // Lista de produtos vendidos
    List<VendaProduto> vendaProdutos = new ArrayList<>();

    // Loop para associar os produtos à venda
    for (ProdutoVenda produtoVenda : vendaRequest.getProdutos()) {
        ProdutoEntity produto = produtoService.getProdutoId(produtoVenda.getId());

        // Verifica se o produto foi encontrado e se a quantidade no estoque é suficiente
        if (produto != null && produto.getQuantidade() >= produtoVenda.getQuantidade()) {
            // Calcula a nova quantidade do produto
            int novaQuantidade = produto.getQuantidade() - produtoVenda.getQuantidade();

            // Verifica se a nova quantidade do produto é válida
            if (novaQuantidade >= 0) {
                // Atualiza a quantidade do produto no estoque
                produto.setQuantidade(novaQuantidade);
                produtoService.atualizarProduto(produto.getId(), produto); // Atualiza o estoque no banco

                // Cria a relação entre produto e venda
                VendaProduto vendaProduto = new VendaProduto();
                vendaProduto.setVenda(novaVenda); // A venda está associada
                vendaProduto.setProduto(produto); // O produto está associado
                vendaProduto.setQuantidade(produtoVenda.getQuantidade()); // Quantidade do produto na venda

                // Adiciona o relacionamento na lista
                vendaProdutos.add(vendaProduto);
            }
        }
    }

    // Salvando a venda primeiro, para garantir que o id da venda é gerado
    novaVenda = vendaService.adicionarVenda(novaVenda, vendaProdutos);

    // Agora, salva todos os produtos vendidos associados à venda
    vendaProdutoService.salvarVendaProdutos(vendaProdutos); // Salva a relação entre venda e todos os produtos de uma vez

    return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
}



    // Atualizar uma venda existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<VendaEntity> atualizarVenda(@PathVariable Long id, @Valid @RequestBody VendaEntity venda) {
        VendaEntity vendaAtualizada = vendaService.atualizarVenda(id, venda);
        return new ResponseEntity<>(vendaAtualizada, HttpStatus.OK);
    }

    // Deletar uma venda por ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        vendaService.deletarVenda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Buscar vendas por intervalo de datas
    @GetMapping("/buscarPorIntervalo/{inicio}/{fim}")
    public ResponseEntity<List<VendaEntity>> buscarVendasPorIntervalo(
            @PathVariable String inicio,
            @PathVariable String fim) {
        LocalDateTime dataInicio = LocalDateTime.parse(inicio);
        LocalDateTime dataFim = LocalDateTime.parse(fim);
        List<VendaEntity> vendas = vendaService.buscarVendasPorIntervalo(dataInicio, dataFim);
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    // Buscar vendas por valor mínimo
    @GetMapping("/buscarPorValorMinimo/{valor}")
    public ResponseEntity<List<VendaEntity>> buscarVendasPorValorMinimo(@PathVariable Float valor) {
        List<VendaEntity> vendas = vendaService.buscarVendasPorValorMinimo(valor);
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    // Listar vendas recentes (ordenadas por data, descendente)
    @GetMapping("/listarRecentes")
    public ResponseEntity<List<VendaEntity>> listarVendasRecentes() {
        List<VendaEntity> vendas = vendaService.listarVendasRecentes();
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }
}
