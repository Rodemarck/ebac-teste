package ebac.rode.teste.controller.dto;

import ebac.rode.teste.entities.Task;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record UpdateTaskRequestDto (
        @NotBlank
        long id,
        @NotBlank
        @Min(value = 3)
        @Max(value = 200)
        String title,
        @NotBlank
        @FutureOrPresent()
        LocalDateTime deadLine,
        @NotBlank()
        Task.Status status,
        @NotBlank()
        @Min(value = 3)
        @Max(value = 8000)
        String description
){
}
