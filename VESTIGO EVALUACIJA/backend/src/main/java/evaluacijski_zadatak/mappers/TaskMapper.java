package evaluacijski_zadatak.mappers;

import evaluacijski_zadatak.dtos.TaskDto;
import evaluacijski_zadatak.entities.Task;

public class TaskMapper {

    public static TaskDto mapToTaskDto(Task task){
        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getCompleted(),
            task.getCreatedAt()
        );
    }

    public static Task mapToTask(TaskDto taskDto){
        return new Task(
            taskDto.getId(),
            taskDto.getTitle(),
            taskDto.getDescription(),
            taskDto.getCompleted(),
            taskDto.getCreatedAt()
        );
    }
}
