
package senac.pi.mercado.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.pi.mercado.model.VendaProduto;

@Repository
public interface VendaProdutoRepository extends JpaRepository<VendaProduto, Integer> {
    
}
