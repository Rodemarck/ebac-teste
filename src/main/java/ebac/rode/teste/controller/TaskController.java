package ebac.rode.teste.controller;

import ebac.rode.teste.controller.dto.*;
import ebac.rode.teste.services.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<ListTaskResponseDto> listTasks(Authentication auth, HttpServletRequest request){
        System.out.println("auth");
        System.out.println(request.getHeader("Authorization"));
        return taskService.list(auth);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskResponseDto> getTask(@PathVariable("id") Long id, Authentication auth){
        return taskService.get(id, auth);
    }

    @PostMapping
    public ResponseEntity<RegisterTaskResponseDto> registerTask(@RequestBody @Valid RegisterTaskRequestDto register, Authentication auth){
        return taskService.register(register, auth);
    }

    @PutMapping
    public ResponseEntity<Void> updateTask(@RequestBody @Valid UpdateTaskRequestDto update, Authentication auth){
        return taskService.update(update, auth);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathParam("id") Long id, Authentication auth){
        return taskService.delete(id, auth);
    }

}
