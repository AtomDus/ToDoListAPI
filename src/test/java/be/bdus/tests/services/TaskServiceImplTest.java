package be.bdus.tests.services;

import be.bdus.entities.Task;
import be.bdus.models.tasks.dtos.TaskDTO;
import be.bdus.repositories.TaskRepository;
import be.bdus.services.impls.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskServiceImplTest {


    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Test
    void shouldUpdateTask() {
        Long taskId = 1L;
        Task existingTask = new Task(taskId, "Old Title", "Old Description");
        Task updatedData = new Task("New Title", "New Description");

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task updated = taskServiceImpl.update(updatedData, taskId);

        assertNotNull(updated);
        assertEquals("New Title", updated.getTitle());
        assertEquals("New Description", updated.getDescription());
        verify(taskRepository).save(existingTask);
    }

    @Test
    void shouldSaveTask() {
        Task task = new Task("title", "desc");
        Task savedTask = new Task(1L, "title", "desc");

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask); // MOCK CORRECT

        Task result = taskServiceImpl.save(task); // DOIT ÊTRE taskServiceImpl

        assertNotNull(result); // C’est ici que tu avais l’erreur
        assertEquals(savedTask.getId(), result.getId());

        assertNotNull(savedTask);
        assertNotNull(savedTask.getTitle());
        assertEquals(task, savedTask);
        assertEquals(task.getTitle(), savedTask.getTitle());
        assertEquals(task.getDescription(), savedTask.getDescription());
    }

    @Test
    void shouldGetAllTasks() {
        List<Task> tasks = List.of(
                new Task(1L, "Title 1", "Desc 1"),
                new Task(2L, "Title 2", "Desc 2")
        );

        Page<Task> page = new PageImpl<>(tasks);
        Pageable pageable = PageRequest.of(0, 10);

        when(taskRepository.findAll(pageable)).thenReturn(page);

        Page<Task> result = taskServiceImpl.getAllTasks(List.of(), pageable);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
    }

    @Test
    void shouldFindTaskById() {
        Task task = new Task(1L, "test", "test");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskDTO actualTask = taskServiceImpl.findById(1L);

        assertNotNull(actualTask);
        assertEquals(task.getTitle(), actualTask.title());
        assertEquals(task.getDescription(), actualTask.description());
    }

    @Test
    void shouldDeleteTask() {
        Task task = new Task(1L, "To Delete", "desc");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(task);

        Task deleted = taskServiceImpl.deleteTask(1L);

        assertNotNull(deleted);
        assertEquals(task.getTitle(), deleted.getTitle());
        verify(taskRepository).delete(task);
    }
}