package senac.pi.mercado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.pi.mercado.model.VendaProduto;

import java.util.List;
import senac.pi.mercado.data.VendaProdutoRepository;

@Service
public class VendaProdutoService {

    @Autowired
    private VendaProdutoRepository vendaProdutoRepository;

    public void salvarVendaProdutos(List<VendaProduto> vendaProdutos) {
        vendaProdutoRepository.saveAll(vendaProdutos); // Salva todos os relacionamentos de uma vez
    }
}
