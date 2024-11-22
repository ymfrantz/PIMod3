package senac.pi.mercado.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaRequest {

    @NotNull
    private LocalDate dataVenda; // Data da venda

    @Positive
    private float valorTotal; // Valor total da venda

    @NotNull
    private List<ProdutoVenda> produtos; // Lista de produtos vendidos

}
