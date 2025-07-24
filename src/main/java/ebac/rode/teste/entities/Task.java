package ebac.rode.teste.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tarefa")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "titulo", nullable = false)
    @NotBlank
    @Min(value = 3,message = "O título da tarefa está muito curto.")
    @Max(value = 200, message = "O título da tarefa está muito grande.")
    private String title;

    @Column(name = "descricao",columnDefinition = "varchar(8000)",nullable = false )
    @NotBlank(message = "O Campo descrição está em branco.")
    @Min(value = 3,message = "A descrição está muito curta.")
    @Max(value = 8000, message = "A descrição está muito longa.")
    private String description;

    @Column(name = "vencimento", nullable = false)
    @NotBlank(message = "O campo data de vencimento está em branco.")
    private LocalDateTime deadLine;

    @Column(name = "status", nullable = false)
    @NotBlank(message = "O Campo status está em branco.")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    @JsonIgnore
    private User user;


    public static enum Status{
        PENDING("Pendente"),IN_PROGRESS("Em andamento"), DONE("Concluída");
        String valor;
        Status(String valor){
            this.valor = valor;
        }
    }
}
