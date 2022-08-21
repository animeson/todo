package com.demo.todo.service;


import com.demo.todo.dto.TaskDto;
import com.demo.todo.entity.Task;
import com.demo.todo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;


    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        if(!taskRepository.existsById(id)){
            log.error("no id = {}", id);
            return new Task();
        } else {
            return taskRepository.getTaskById(id);
        }
    }

    @Override
    public Task saveTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTask(taskDto.getTask());
        task.setCreationDate(LocalDate.now());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task editTaskById(Long id, TaskDto taskDto) {
        Task editTask = taskRepository.getTaskById(id);
        editTask.setTask(taskDto.getTask());
        return taskRepository.save(editTask);
    }
}
