package com.example.task_manager.service;

import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.entity.Task;
import com.example.task_manager.repository.TaskRepo;
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

        return taskRepo.save(task);
    }
}
