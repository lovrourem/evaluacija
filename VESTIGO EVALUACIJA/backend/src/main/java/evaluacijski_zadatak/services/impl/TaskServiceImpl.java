package evaluacijski_zadatak.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import evaluacijski_zadatak.dtos.TaskDto;
import evaluacijski_zadatak.entities.Task;
import evaluacijski_zadatak.exceptions.ResourceNotFoundException;
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

    @Override
    public TaskDto getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("There is no task with the given id : " + taskId));
        return TaskMapper.mapToTaskDto(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map((task) -> TaskMapper.mapToTaskDto(task))
            .collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto updatedTask) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("There is no task with the given id"));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.getCompleted());

        Task updatedTaskObj = taskRepository.save(task);
        return TaskMapper.mapToTaskDto(updatedTaskObj);
    }

}
