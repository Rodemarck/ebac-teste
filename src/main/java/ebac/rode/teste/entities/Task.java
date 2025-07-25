package ebac.rode.teste.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 3, max = 200)
    private String title;

    @Column(name = "descricao",columnDefinition = "varchar(8000)",nullable = false )
    @Length(min = 3,max = 8000)
    private String description;

    @Column(name = "vencimento", nullable = false)
    @NotNull
    @FutureOrPresent()
    private LocalDateTime deadLine;

    @Column(name = "status", nullable = false)
    @NotNull()
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

    @Override
    public String toString() {
        return "Task(" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", status=" + status +
                ')';
    }
}
