/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package senac.pi.mercado.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senac.pi.mercado.model.ProdutoEntity;
import senac.pi.mercado.service.ProdutoService;

@RestController // como um manipulador de solicitação HTTP
// e permite que o Spring o reconheça como um serviço REST.
@CrossOrigin(origins = "*") 
@RequestMapping("/produto")
public class ProdutoController {
    
    @Autowired
    ProdutoService produtoService;
    
    
    @GetMapping("/buscarPorNome/{nome}")
    public ResponseEntity<List> AllProdutoNome(@PathVariable String nome){
        
        List<ProdutoEntity> produtos = produtoService.AllProdutoNome(nome);
        
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllProdutos(){
        
        List<ProdutoEntity> produtos = produtoService.listarProdutos();
        
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
    
    @PostMapping("/adicionar")
    public ResponseEntity<ProdutoEntity> addProduto(@Valid @RequestBody ProdutoEntity produto){
        
        var novoProduto = produtoService.criarProduto(produto);
        
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoEntity> atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoEntity produto){
        
        var produtoAtualizado = produtoService.atualizarProduto(id, produto);
        
        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarProduto(@PathVariable Integer id){
        
        produtoService.deletarProduto(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
