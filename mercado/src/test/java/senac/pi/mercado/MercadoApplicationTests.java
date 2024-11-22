package senac.pi.mercado;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import senac.pi.mercado.service.ProdutoService;

@SpringBootTest
public class MercadoApplicationTests {

    @Autowired
    private ProdutoService produtoService; // Verifique se todos os beans são injetados corretamente

    @Test
    public void contextLoads() {
        // Verifique se o contexto carrega corretamente
        assertNotNull(produtoService);
    }
}

