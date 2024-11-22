package senac.pi.mercado.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVenda {

    @NotNull
    private Integer id; // ID do produto (referência ao produto existente no banco)

    @Positive
    private Integer quantidade; // Quantidade do produto a ser vendido

    @NotBlank
    private String nome; // Nome do produto

    @NotBlank
    private String descricao; // Descrição do produto

    @Positive
    private float valor; // Valor do produto

}
