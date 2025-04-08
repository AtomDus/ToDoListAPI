package be.bdus.models.tasks.dtos;

import be.bdus.entities.Task;

public record TaskDTO(
    Long id,
    String title,
    String description
) {
    public static TaskDTO fromTask(Task task) {
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription());

    }
}
