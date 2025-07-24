package ebac.rode.teste.controller.dto;

import ebac.rode.teste.entities.Task;

import java.util.List;

public record ListTaskResponseDto(List<Task> tasks) {
}
