
package senac.pi.mercado.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import senac.pi.mercado.model.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer>{
        ProdutoEntity findByNome(String nome);

    List<ProdutoEntity> findByNomeStartingWith(String nome);

    List<ProdutoEntity> findByNomeEndingWith(String nome);

    List<ProdutoEntity> findByNomeContaining(String nome);

    List<ProdutoEntity> findByOrderByNomeAsc();

    List<ProdutoEntity> findByOrderByNomeDesc();
}
