package com.tsimo.taskservice.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Buy groceries", "fruits and veggies");
        task.setId(1L);
    }

    @Test
    void createTask_shouldReturnTaskResponse() {
        TaskCreateRequest taskCreateRequest = new TaskCreateRequest("Buy groceries", "fruits and veggies");
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskResponse response = taskService.createTask(taskCreateRequest);

        assertThat(response).isNotNull();
        assertNotNull(response);
        assertNotNull(response.id());

        assertThat(response.id()).isEqualTo(1L);
        assertEquals(1L, response.id());
        assertNotEquals(2L, response.id());

        assertEquals("Buy groceries", response.title());
        assertEquals("fruits and veggies", response.description());
        assertThat(TaskStatus.OPEN).isEqualTo(response.status());

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void findAll_shouldReturnAllTasks() {
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<TaskResponse> result = taskService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).title()).isEqualTo("Buy groceries");
        assertThat(result.get(0).description()).isEqualTo("fruits and veggies");
        assertThat(result.get(0).status()).isEqualTo(TaskStatus.OPEN);
    }

    @Test
    void findById_shouldReturnTask_whenExists() throws TaskNotFoundException {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskResponse response = taskService.findById(1L);

        assertThat(response.id()).isEqualTo(1L);
    }

    @Test
    void findById_shouldThrow_whenNotFound() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.findById(99L))
                .isInstanceOf(TaskNotFoundException.class);
    }

     @Test
     void updateTask_shouldReturnUpdatedTask() throws TaskNotFoundException {
         TaskUpdateRequest updateRequest = new TaskUpdateRequest("Buy groceries and snacks", "fruits, veggies and chips", TaskStatus.IN_PROGRESS);
         when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
         when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskResponse updatedTask = taskService.updateTask(1L, updateRequest);
        assertThat(updatedTask.title()).isEqualTo("Buy groceries and snacks");
        assertThat(updatedTask.description()).isEqualTo("fruits, veggies and chips");
        assertThat(updatedTask.status()).isEqualTo(TaskStatus.IN_PROGRESS);
        }
}
