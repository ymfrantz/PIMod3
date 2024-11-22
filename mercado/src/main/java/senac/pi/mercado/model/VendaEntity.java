package senac.pi.mercado.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Entity
@Table(name = "venda")
public class VendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // ID da venda

    @NotNull
    private LocalDate dataVenda; // Data da venda

    @Positive
    private float valorTotal; // Valor total da venda

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<VendaProduto> produtos; // Relacionamento com a tabela intermediária

}
