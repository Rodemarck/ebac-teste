package ebac.rode.teste.controller;

import ebac.rode.teste.controller.dto.ListTaskResponseDto;
import ebac.rode.teste.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    //@GetMapping
    public ResponseEntity<ListTaskResponseDto> listTasks(){
        return taskService.list();
    }

    @GetMapping
    public String teste(){
        return "sou proibido";
    }

}
