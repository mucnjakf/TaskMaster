package com.devlab.taskmaster.repository;

import com.devlab.taskmaster.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
}
