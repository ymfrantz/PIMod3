
package senac.pi.mercado.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.pi.mercado.model.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer>{
    
    
    boolean existsByUsernameAndPassword(String username, String password);
    
    List<LoginEntity> findByUsernameEndingWith(String suffix);
}
