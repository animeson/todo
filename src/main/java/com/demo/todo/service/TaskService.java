package com.demo.todo.service;



import com.demo.todo.dto.TaskDto;
import com.demo.todo.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTask();
    Task getTaskById(Long id);
    Task saveTask(TaskDto taskDto);
    void deleteTaskById(Long id);
    Task editTaskById(Long id, TaskDto taskDto);
}
