package com.demo.todo.repository;


import com.demo.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @NonNull
    List<Task> findAll();

    Task getTaskById(Long id);

    void deleteById(@NonNull Long id);
    void deleteAll();
}
