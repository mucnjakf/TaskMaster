package com.devlab.taskmaster.repository;

import com.devlab.taskmaster.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
