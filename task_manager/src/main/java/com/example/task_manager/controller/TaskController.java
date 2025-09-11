package com.example.task_manager.controller;

import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.entity.Task;
import com.example.task_manager.repository.TaskRepo;
import com.example.task_manager.response.TaskResponse;
import com.example.task_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<List<TaskResponse>> getAllTasks(){

        List<Task> allTasks = taskService.getAllTasks();

        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : allTasks) {

            taskResponses.add(new TaskResponse(new TaskDTO(task.getTaskId(), task.getTaskTitle(), task.getDescription())));
        }
        return new ResponseEntity<>(taskResponses, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<TaskResponse> postNewTask(@RequestBody TaskDTO taskDTO){

        Task task = taskService.postTask(taskDTO);
        TaskDTO taskDTO1 = new TaskDTO(task.getTaskId(), task.getTaskTitle(), task.getDescription());
        return new ResponseEntity<>(new TaskResponse(taskDTO1),HttpStatus.OK);
    }
}
