package com.devlab.taskmaster.controller;

import com.devlab.taskmaster.dto.task.CreateTaskDTO;
import com.devlab.taskmaster.dto.task.TaskDTO;
import com.devlab.taskmaster.dto.task.UpdateTaskDTO;
import com.devlab.taskmaster.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<TaskDTO> taskDTOs = taskService.getTasks();

        return ResponseEntity.ok(taskDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Integer id) {
        TaskDTO taskDTO = taskService.getTask(id);

        return ResponseEntity.ok(taskDTO);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody CreateTaskDTO createTaskDTO) {
        TaskDTO taskDTO = taskService.createTask(createTaskDTO);

        return ResponseEntity.created(URI.create("/api/tasks/" + taskDTO.getId())).body(taskDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Integer id, @RequestBody UpdateTaskDTO updateTaskDTO) {
        taskService.updateTask(id, updateTaskDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }
}
