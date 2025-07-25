package ebac.rode.teste.controller.dto;

import ebac.rode.teste.entities.Task;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record RegisterTaskResponseDto(
        long id
) {
}
