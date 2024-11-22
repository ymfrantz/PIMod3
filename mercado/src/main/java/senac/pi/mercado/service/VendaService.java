
package senac.pi.mercado.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.pi.mercado.data.VendaRepository;
import senac.pi.mercado.model.VendaEntity;

import java.time.LocalDateTime;
import java.util.List;
import senac.pi.mercado.model.VendaProduto;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public List<VendaEntity> listarVendas() {
        return vendaRepository.findAll();
    }

    public VendaEntity buscarVendaPorId(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada."));
    }

    public VendaEntity adicionarVenda(VendaEntity novaVenda, List<VendaProduto> vendaProdutos) {
        // Relacionando produtos à venda
        novaVenda.setProdutos(vendaProdutos);
        return vendaRepository.save(novaVenda); // Salva a venda e os produtos associados
    }

    public VendaEntity atualizarVenda(Long id, VendaEntity venda) {
        VendaEntity existente = buscarVendaPorId(id);
        existente.setDataVenda(venda.getDataVenda());
        existente.setProdutos(venda.getProdutos());
        existente.setValorTotal(venda.getValorTotal());
        return vendaRepository.save(existente);
    }

    public void deletarVenda(Long id) {
        vendaRepository.deleteById(id);
    }

    public List<VendaEntity> buscarVendasPorIntervalo(LocalDateTime inicio, LocalDateTime fim) {
        return vendaRepository.findByDataVendaBetween(inicio, fim);
    }

    public List<VendaEntity> buscarVendasPorValorMinimo(Float valor) {
        return vendaRepository.findByValorTotalGreaterThan(valor);
    }

    public List<VendaEntity> listarVendasRecentes() {
        return vendaRepository.findByOrderByDataVendaDesc();
    }
}
