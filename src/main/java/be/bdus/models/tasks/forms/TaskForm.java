package be.bdus.models.tasks.forms;

import be.bdus.entities.Task;

public record TaskForm(
    String title,
    String description
) {
    public Task toTask() {
        return new Task(title, description);
    }

}
