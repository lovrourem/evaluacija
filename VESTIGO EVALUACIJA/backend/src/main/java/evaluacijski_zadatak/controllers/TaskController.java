package evaluacijski_zadatak.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evaluacijski_zadatak.dtos.TaskDto;
import evaluacijski_zadatak.services.TaskService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;

    //REST API za dodavanje taska
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        TaskDto savedTaskDto = taskService.createTask(taskDto);
        return new ResponseEntity<>(savedTaskDto, HttpStatus.CREATED);
    }
}
