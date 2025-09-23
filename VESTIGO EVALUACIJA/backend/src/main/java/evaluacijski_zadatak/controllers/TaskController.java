package evaluacijski_zadatak.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public ResponseEntity<TaskDto> createTask(@RequestHeader("Auth") String token,
                                @RequestBody TaskDto taskDto){
        TaskDto savedTaskDto = taskService.createTask(taskDto,token);
        return new ResponseEntity<>(savedTaskDto, HttpStatus.CREATED);
    }

    //REST API za dohvacanje svih taskova
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestHeader("Auth") String token){
        List<TaskDto> tasks = taskService.getAllTasks(token);
        return ResponseEntity.ok(tasks);
    }

    //REST API za brisanje taska
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@RequestHeader ("Auth") String token,
                            @PathVariable("id") Long taskId){
        taskService.deleteTask(taskId, token);
        return ResponseEntity.ok("Task deleted succesfully");
    }

    //REST API za uređivanje taska
    @PutMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable ("id") Long taskId,
                                                    @RequestBody TaskDto updatedTask,
                                                    @RequestHeader ("Auth") String token){
        TaskDto taskDto = taskService.updateTask(taskId, updatedTask, token);
        return ResponseEntity.ok(taskDto);
    }

    //REST API za završavanje taska
    @PostMapping("/complete/{id}")
    public ResponseEntity<TaskDto> completeTask(@PathVariable ("id") Long taskId,
                                                    @RequestHeader ("Auth") String token){
        
        TaskDto taskDto = taskService.completeTask(taskId, token);
        return ResponseEntity.ok(taskDto);
    }


} 
