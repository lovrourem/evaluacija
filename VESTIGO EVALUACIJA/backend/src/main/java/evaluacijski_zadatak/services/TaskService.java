package evaluacijski_zadatak.services;

import java.util.List;

import evaluacijski_zadatak.dtos.TaskDto;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);
    TaskDto getTaskById(Long taskId);
    List<TaskDto> getAllTasks();
    TaskDto updateTask(Long taskId, TaskDto updatedTask);
}
