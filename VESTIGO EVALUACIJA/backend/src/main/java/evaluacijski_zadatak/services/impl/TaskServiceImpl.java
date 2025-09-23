package evaluacijski_zadatak.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import evaluacijski_zadatak.dtos.TaskDto;
import evaluacijski_zadatak.entities.Task;
import evaluacijski_zadatak.entities.User;
import evaluacijski_zadatak.exceptions.ResourceNotFoundException;
import evaluacijski_zadatak.mappers.TaskMapper;
import evaluacijski_zadatak.repositories.TaskRepository;
import evaluacijski_zadatak.repositories.UserRepository;
import evaluacijski_zadatak.services.JwtService;
import evaluacijski_zadatak.services.TaskService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private JwtService jwtService;

    @Override
    public TaskDto createTask(TaskDto taskDto, String token) {

        Long userId = jwtService.extractUserId(token);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Task newTask = TaskMapper.mapToTask(taskDto);
        newTask.setUser(user);
        newTask.setCompleted(false);
        newTask.setCreatedAt(LocalDateTime.now());
        taskRepository.save(newTask);
        return TaskMapper.mapToTaskDto(newTask);
    }

    @Override
    public List<TaskDto> getAllTasks(String token) {
        Long userId = jwtService.extractUserId(token);
        userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Task> tasks = taskRepository.findAllByUserId(userId);
        return tasks.stream().map((task) -> TaskMapper.mapToTaskDto(task))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long taskId, String token) {
        Long userId = jwtService.extractUserId(token);
        userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("There is no task with the given id : " + taskId));
        
        if(!task.getUser().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to delete this task");
        }
        
        taskRepository.delete(task);
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto updatedTask, String token) {
        Long userId = jwtService.extractUserId(token);
        userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("There is no task with the given id"));

        if(!task.getUser().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to edit this task");
        }

        if (task.getCompleted()) {
            throw new RuntimeException("Completed tasks cannot be edited");
        }

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());

        Task updatedTaskObj = taskRepository.save(task);
        return TaskMapper.mapToTaskDto(updatedTaskObj);
    }

    @Override
    public TaskDto completeTask(Long taskId, String token) {
        Long userId = jwtService.extractUserId(token);
        userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Task task = taskRepository.findById(taskId)
                        .orElseThrow(() -> new ResourceNotFoundException("There is no task with the given id"));

        if(!task.getUser().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to complete this task");
        }

        if (task.getCompleted()) {
            throw new RuntimeException("Completed tasks cannot be completed again");
        }

        task.setCompleted(true);
        Task updatedTaskObj = taskRepository.save(task);
        return TaskMapper.mapToTaskDto(updatedTaskObj);
    }

    @Override
    public TaskDto getTaskById(Long taskId, String token) {
        Long userId = jwtService.extractUserId(token);
        userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Task task = taskRepository.findById(taskId)
                        .orElseThrow(() -> new ResourceNotFoundException("There is no task with the given id"));

        if(!task.getUser().getId().equals(userId)){
            throw new RuntimeException("You are not allowed to view this task");
        }

        return TaskMapper.mapToTaskDto(task);
    }

    
}
