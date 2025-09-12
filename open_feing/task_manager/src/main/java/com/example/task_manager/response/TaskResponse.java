package com.example.task_manager.response;

import com.example.task_manager.dto.TaskDTO;
import com.example.task_manager.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private TaskDTO taskDTO;
}
