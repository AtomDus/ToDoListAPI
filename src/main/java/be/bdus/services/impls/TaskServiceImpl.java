package be.bdus.services.impls;

import be.bdus.entities.Task;
import be.bdus.models.tasks.dtos.TaskDTO;
import be.bdus.repositories.TaskRepository;
import be.bdus.services.TaskService;
import be.bdus.utils.requests.SearchParam;
import be.bdus.utils.specifications.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    @Override
    public Task update(Task task, Long id) {
        Optional<Task> existingEvent = taskRepository.findById(id);
        if (existingEvent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }

        Task updatedEvent = existingEvent.get();
        updatedEvent.setTitle(task.getTitle());
        updatedEvent.setDescription(task.getDescription());

        return taskRepository.save(updatedEvent);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Page<Task> getAllTasks(List<SearchParam<Task>> searchParams, Pageable pageable) {
        if (searchParams.isEmpty()) {
            return taskRepository.findAll(pageable);
        }
        return taskRepository.findAll(
                Specification.allOf(
                        searchParams.stream()
                                .map(SearchSpecification::search)
                                .toList()
                ),
                pageable
        );
    }

    @Override
    public TaskDTO findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));
        return TaskDTO.fromTask(task);
    }

    public Task deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));
        taskRepository.delete(task);
        return task;
    }
}
