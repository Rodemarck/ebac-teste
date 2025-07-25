package ebac.rode.teste.controller.dto;

import ebac.rode.teste.entities.Task;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

public record RegisterTaskRequestDto(
        @NotNull
        @Length(max = 200, min= 3)
        String title,
        @NotNull
        @FutureOrPresent()
        LocalDateTime deadLine,
        @NotNull()
        Task.Status status,
        @NotNull()
        @Length(min = 3,max = 8000)
        String description
) implements Serializable {
}
