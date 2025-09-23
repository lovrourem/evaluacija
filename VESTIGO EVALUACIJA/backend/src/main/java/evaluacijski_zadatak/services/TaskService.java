package evaluacijski_zadatak.services;

import java.util.List;

import evaluacijski_zadatak.dtos.TaskDto;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto, String token);
    List<TaskDto> getAllTasks(String token);
    TaskDto updateTask(Long taskId, TaskDto updatedTask);
    void deleteTask(Long taskId, String token);
}
