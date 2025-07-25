package ebac.rode.teste.services;

import ebac.rode.teste.controller.dto.*;
import ebac.rode.teste.entities.Task;
import ebac.rode.teste.entities.User;
import ebac.rode.teste.exceptions.TaskNotFoundException;
import ebac.rode.teste.exceptions.UserNotFoundException;
import ebac.rode.teste.repositories.TaskRepository;
import ebac.rode.teste.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    private User getUser(Authentication auth){
        if (auth == null) {
            throw new IllegalStateException("Authentication não encontrada. Verifique se o token JWT está sendo enviado corretamente.");
        }
        return userRepository.findByEmail(auth.getName()).orElseThrow(UserNotFoundException::new);
    }

    public ResponseEntity<ListTaskResponseDto> list(Authentication auth) {
        var user = getUser(auth);
        return ResponseEntity.ok(new ListTaskResponseDto(taskRepository.findByUser(user)));
    }

    public ResponseEntity<GetTaskResponseDto> get(Long id, Authentication auth) {
        var user = getUser(auth);
        var task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        if(!task.getUser().equals(user)){
            throw new TaskNotFoundException();
        }
        return ResponseEntity.ok(new GetTaskResponseDto(task));
    }

    public ResponseEntity<RegisterTaskResponseDto> register(RegisterTaskRequestDto register, Authentication auth) {
        var user = getUser(auth);
        var task = Task.builder()
                .title(register.title())
                .status(register.status())
                .deadLine(register.deadLine())
                .description(register.description())
                .user(user)
                .build();
        System.out.println(task);

        if (task.getDescription().length() < 3 || task.getDescription().length() > 8000) {
            throw new IllegalArgumentException("Descrição deve ter entre 3 e 8000 caracteres");
        }
        task = taskRepository.save(task);
        return ResponseEntity.ok(new RegisterTaskResponseDto(task.getId()));
    }

    public ResponseEntity<Void> update(UpdateTaskRequestDto update, Authentication auth) {
        var user = getUser(auth);
        var task = taskRepository.findById(update.id()).orElseThrow(TaskNotFoundException::new);
        if(!task.getUser().equals(user)){
            throw new TaskNotFoundException();
        }
        task.setDescription(update.description());
        task.setTitle(update.title());
        task.setStatus(update.status());
        task.setDeadLine(update.deadLine());
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<Void> delete(Long id, Authentication auth) {
        var user = getUser(auth);
        var task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        if(!task.getUser().equals(user)){
            throw new TaskNotFoundException();
        }
        taskRepository.deleteById(id);
        return ResponseEntity.ok(null);
    }
}
