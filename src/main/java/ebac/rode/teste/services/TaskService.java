package ebac.rode.teste.services;

import ebac.rode.teste.controller.dto.ListTaskResponseDto;
import ebac.rode.teste.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity<ListTaskResponseDto> list() {
        return null;
    }
}
