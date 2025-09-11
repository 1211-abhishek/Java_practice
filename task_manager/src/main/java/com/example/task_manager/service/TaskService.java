package com.example.task_manager.service;

import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.entity.Task;
import com.example.task_manager.entity.Users;
import com.example.task_manager.repository.TaskRepo;
import com.example.task_manager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Task> getAllTasks() {

        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication()).getUsername();
        List<Task> tasks = taskRepo.findByUser_UserName(username);
//        return null;
        return tasks;
    }

    public Task postTask(TaskDTO taskDTO) {

        Task task = new Task();
        task.setTaskId(taskDTO.getTaskId());
        task.setTaskTitle(taskDTO.getTaskTitle());
        task.setDescription(taskDTO.getDescription());
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        Users users = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(users);

        return taskRepo.save(task);
    }
}
