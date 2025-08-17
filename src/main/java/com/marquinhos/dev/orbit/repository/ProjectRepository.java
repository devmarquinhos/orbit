package com.marquinhos.dev.orbit.repository;

import com.marquinhos.dev.orbit.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
