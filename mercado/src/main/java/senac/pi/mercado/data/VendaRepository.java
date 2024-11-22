package senac.pi.mercado.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import senac.pi.mercado.model.VendaEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<VendaEntity, Long> {

    // Busca vendas realizadas em uma data específica
    List<VendaEntity> findByDataVenda(LocalDateTime dataVenda);

    // Busca vendas realizadas em um intervalo de datas
    List<VendaEntity> findByDataVendaBetween(LocalDateTime inicio, LocalDateTime fim);

    // Busca vendas ordenadas por data (mais recentes primeiro)
    List<VendaEntity> findByOrderByDataVendaDesc();

    // Busca vendas por valor total maior que um valor especificado
    List<VendaEntity> findByValorTotalGreaterThan(Float valor);

    // Busca vendas por um produto específico (usando nome do produto)
    @Query("SELECT v FROM VendaEntity v JOIN v.produtos vp JOIN vp.produto p WHERE p.nome = :nome")
    List<VendaEntity> findByProdutoNome(@Param("nome") String nome);

    // Busca vendas que contenham um produto específico pelo ID do produto
    @Query("SELECT v FROM VendaEntity v JOIN v.produtos p WHERE p.id = :produtoId")
    List<VendaEntity> findByProdutoId(@Param("produtoId") Long produtoId);
}