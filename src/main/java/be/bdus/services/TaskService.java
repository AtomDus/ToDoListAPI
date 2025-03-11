package be.bdus.services;

import be.bdus.entities.Task;
import be.bdus.models.tasks.dtos.TaskDTO;
import be.bdus.utils.requests.SearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface TaskService {

    Task update(Task task, Long id);

    Task save(Task task);

    Page<Task> getAllTasks(List<SearchParam<Task>> searchParams, Pageable pageable);

    TaskDTO findById(Long id);

    Task deleteTask(Long id);
}
