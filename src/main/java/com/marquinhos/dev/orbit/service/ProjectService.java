package com.marquinhos.dev.orbit.service;

import com.marquinhos.dev.orbit.model.Project;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

public class ProjectService {
    private final List<Project> projects = new ArrayList<>();
    private Integer nextId = 1;

    public Project create(Project project) {
        if (hasText(project.getName())) {
            project.setId(nextId++);
            projects.add(project);
            return project;
        } else {
            throw new IllegalArgumentException("Project name must not be null or empty.");
        }
    }

    public List<Project> findAll(){
        return new ArrayList<>(projects);
    }
}
