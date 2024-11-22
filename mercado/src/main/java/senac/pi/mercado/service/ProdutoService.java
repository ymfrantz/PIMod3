package senac.pi.mercado.service;

import com.api.atividade02.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.pi.mercado.data.ProdutoRepository;
import senac.pi.mercado.model.ProdutoEntity;


@Service
public class ProdutoService {
    
    @Autowired
    ProdutoRepository produtoRepository;
    
    
    
    
    public ProdutoEntity criarProduto(ProdutoEntity produto){
        
        produto.setId(null);
        produtoRepository.save(produto);
        return produto;
    }
    
    public List<ProdutoEntity> listarProdutos(){
        
        return produtoRepository.findAll();   
    }
    
    public List<ProdutoEntity> AllProdutoNome(String nome){
        
        return produtoRepository.findByNomeContaining(nome); 
    }
    
    
    public ProdutoEntity getProdutoId(Integer id){
        
        return produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado " + id));
    }
    
    public void deletarProduto(Integer produtoId){
        
        ProdutoEntity produto = getProdutoId(produtoId); // valida encontrando o produto
        produtoRepository.deleteById(produto.getId()); // depois deleta caso encontre
    } 
    
    public List<ProdutoEntity> getProdutoTitulo(String nome){
     
        return produtoRepository.findByNomeContaining(nome);
    }
    
    public ProdutoEntity atualizarProduto(Integer id, ProdutoEntity produtoRequest){
        ProdutoEntity produto = getProdutoId(id);
        produto.setNome(produtoRequest.getNome());
        produto.setDescricao(produtoRequest.getDescricao());
        produto.setQuantidade(produtoRequest.getQuantidade());
        produto.setValidade(produtoRequest.getValidade());
        produto.setValor(produtoRequest.getValor());
        
        produtoRepository.save(produto);
        return produto;
    }
    
}

