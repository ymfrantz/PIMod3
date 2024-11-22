package senac.pi.mercado.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "venda_produto")
public class VendaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Garante o auto incremento
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private VendaEntity venda;

    private int quantidade;
    
    // Getters and Setters
}
