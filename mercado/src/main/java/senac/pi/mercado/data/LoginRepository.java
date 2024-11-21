
package senac.pi.mercado.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senac.pi.mercado.model.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer>{
    
    LoginEntity findByUser(String user);

    List<LoginEntity> findByUserStartingWith(String user);

    List<LoginEntity> findByUserEndingWith(String user);

    List<LoginEntity> findByUserContaining(String user);

    List<LoginEntity> findByOrderByUserAsc();

    List<LoginEntity> findByOrderByUserDesc();
}
