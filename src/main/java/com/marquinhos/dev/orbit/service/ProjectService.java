package com.marquinhos.dev.orbit.service;

import com.marquinhos.dev.orbit.model.Project;
import com.marquinhos.dev.orbit.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Project create(Project project) {
        if (project.getName() == null || project.getName().isBlank()) {
            throw new IllegalArgumentException("Project name must not be null or empty.");
        }
        return repository.save(project);
    }

    public List<Project> findAll() {
        return repository.findAll();
    }

    public Optional<Project> findById(Integer id) {
        return repository.findById(id);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Project with id " + id + " not found.");
        }
        repository.deleteById(id);
    }
}
