package com.example.task.controller;

import com.example.task.model.Task;
import com.example.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Get All created projects
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try {
            List<Task> projects = taskService.getAll();
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    /**
     * Save a project.
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Task task) {
        try {
            return ResponseEntity.ok(taskService.save(task));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    /**
     * Get an action by ID.
     */
    @GetMapping("/get-by-id")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(taskService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    /**
     * Delete a post by ID.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        try {
            taskService.deleteById(id);
            return ResponseEntity.ok(Collections.singletonMap("Message", "Project deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }
}
