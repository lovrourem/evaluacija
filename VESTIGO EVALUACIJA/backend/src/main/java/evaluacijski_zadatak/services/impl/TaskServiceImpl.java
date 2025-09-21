package evaluacijski_zadatak.services.impl;

import org.springframework.stereotype.Service;
import evaluacijski_zadatak.dtos.TaskDto;
import evaluacijski_zadatak.entities.Task;
import evaluacijski_zadatak.mappers.TaskMapper;
import evaluacijski_zadatak.repositories.TaskRepository;
import evaluacijski_zadatak.services.TaskService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Override
    public TaskDto createTask(TaskDto taskDto) {

        Task task = TaskMapper.mapToTask(taskDto);
        Task savedTask = taskRepository.save(task);
        TaskDto savedTaskDto = TaskMapper.mapToTaskDto(savedTask);
        return savedTaskDto;
    }

}
