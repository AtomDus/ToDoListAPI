package be.bdus.controllers;

import be.bdus.entities.Task;
import be.bdus.models.tasks.dtos.TaskDTO;
import be.bdus.models.tasks.forms.TaskForm;
import be.bdus.services.TaskService;
import be.bdus.utils.CustomPage;
import be.bdus.utils.requests.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public String createTask() {
        return "Task created";
    }

    @GetMapping
    public ResponseEntity<CustomPage<TaskDTO>> getTasks(
            @RequestParam Map<String, String> params,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        List<SearchParam<Task>> searchParams = SearchParam.create(params);

        Page<Task> tickets = taskService.getAllTasks(searchParams,
                PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, sort)));

        List<TaskDTO> dtos = tickets.getContent().stream()
                .map(TaskDTO::fromTask)
                .toList();

        CustomPage<TaskDTO> result = new CustomPage<>(dtos, tickets.getTotalPages(), tickets.getNumber() + 1);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskForm taskForm) {
        return ResponseEntity.ok(TaskDTO.fromTask(taskService.update(taskForm.toTask(), id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
