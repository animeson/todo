package com.demo.todo.service;


import com.demo.todo.dto.TaskDto;
import com.demo.todo.entity.Task;
import com.demo.todo.exception.ApiRequestException;
import com.demo.todo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;


    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        if (!taskRepository.existsById(id)) {
            log.error("no id = {}", id);
            throw new ApiRequestException("no id = " + id);
        } else {
            return taskRepository.getTaskById(id);
        }
    }

    @Override
    public Task saveTask(TaskDto taskDto) {
        if (taskDto.getTask() == null || taskDto.getTask().equals("")) {
            log.error("the Task cannot be empty field");
            throw new ApiRequestException("the Task cannot be empty field");
        } else {
            Task task = new Task();
            task.setTask(taskDto.getTask());
            task.setCreationDate(LocalDate.now());
            return taskRepository.save(task);
        }
    }

    @Override
    public void deleteTaskById(Long id) {
        if (!taskRepository.existsById(id)) {
            log.error("no id = {}, or data delete", id);
            throw new ApiRequestException("no id = " + id + "," + "or data delete");
        } else {
            taskRepository.deleteById(id);
        }
    }

    @Override
    public Task editTaskById(Long id, TaskDto taskDto) {
        Task editTask = taskRepository.getTaskById(id);
        if (taskDto.getTask() == null || taskDto.getTask().equals("") ||
                !taskRepository.existsById(id) ) {

            log.error("Invalid data");
            throw new ApiRequestException("Invalid data");
        } else {
            editTask.setTask(taskDto.getTask());
            return taskRepository.save(editTask);
        }
    }

    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }


}

