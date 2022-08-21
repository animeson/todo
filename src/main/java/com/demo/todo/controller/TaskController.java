package com.demo.todo.controller;


import com.demo.todo.dto.TaskDto;
import com.demo.todo.service.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task")
@AllArgsConstructor
@CrossOrigin("*")
public class TaskController {
    private final TaskServiceImpl taskService;

    @GetMapping
    public ResponseEntity<?> getAllTask() {
        return ResponseEntity.ok().body(taskService.getAllTask());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<?> editTask(@PathVariable Long id,
                                      @RequestBody TaskDto taskDto){
        return ResponseEntity.ok().body(taskService.editTaskById(id, taskDto));
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto){
        return ResponseEntity.ok().body(taskService.saveTask(taskDto));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok().body("Task delete");
    }
}
