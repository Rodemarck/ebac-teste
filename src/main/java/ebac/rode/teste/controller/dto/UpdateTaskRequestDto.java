package ebac.rode.teste.controller.dto;

import ebac.rode.teste.entities.Task;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record UpdateTaskRequestDto (
        long id,
        @NotBlank
        @Length(min = 3,max = 200)
        String title,
        @FutureOrPresent()
        LocalDateTime deadLine,
        Task.Status status,
        @Length(min = 3, max = 8000)
        String description
){
}
