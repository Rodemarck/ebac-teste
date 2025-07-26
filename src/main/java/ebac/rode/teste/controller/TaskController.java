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
        System.out.println("list");
        return taskService.list(auth);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskResponseDto> getTask(@PathVariable("id") Long id, Authentication auth){
        System.out.println("get");
        return taskService.get(id, auth);
    }

    @PostMapping
    public ResponseEntity<RegisterTaskResponseDto> registerTask(@RequestBody @Valid RegisterTaskRequestDto register, Authentication auth){
        System.out.println("register");
        return taskService.register(register, auth);
    }

    @PatchMapping
    public ResponseEntity updateTask(@RequestBody @Valid UpdateTaskRequestDto update, Authentication auth){
        System.out.println("update");
        return taskService.update(update, auth);
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id,HttpServletRequest request, Authentication auth){
        System.out.println("delete");
        System.out.println(request.getRequestURI());
        return taskService.delete(id, auth);
    }

}
