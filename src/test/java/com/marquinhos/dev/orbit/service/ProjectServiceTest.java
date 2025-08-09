package com.marquinhos.dev.orbit.service;

import com.marquinhos.dev.orbit.model.Project;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectServiceTest {
    ProjectService service = new ProjectService();

    @Test
    void shouldCreateProjectWithNameAndDescription(){
        // GIVEN - dados de entrada
        Project project = new Project("Orbit", "Project Aggregator");

        // WHEN - ação que será realizada
        Project createdProject = service.create(project);

        // THEN - resultado esperado
        assertNotNull(createdProject);
        assertEquals("Orbit", createdProject.getName());
        assertEquals("Project Aggregator", createdProject.getDescription());
    }

    @Test
    void shouldReturnProjectWithIdWhenSaved(){
        // Given
        Project input = new Project();
        input.setName("My test project");

        // When
        Project result = service.create(input);

        // Then
        assertNotNull(result.getId(), "ID should not be null after saving the project");
    }

    @Test
    void shouldThrownExceptionIfProjectNameIsNull(){
        // Given
        Project input = new Project();
        input.setName(null);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            service.create(input);
        });
    }

    @Test
    void shouldThrownExceptionWhenProjectNameIsEmpty(){
        // Given
        Project input = new Project();
        input.setName("");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            service.create(input);
        });
    }

    @Test
    void shouldThrownExceptionWhenProjectNameIsBlank(){
        // Given
        Project input = new Project();
        input.setName("   ");

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            service.create(input);
        });
    }

    @Test
    void shouldReturnAllProjects(){
        // Given
        Project p1 = new Project();
        p1.setName("Orbit");

        Project p2 = new Project();
        p2.setName("Moon");

        service.create(p1);
        service.create(p2);

        // When
        List<Project> allProjects = service.findAll();

        // Then
        assertEquals(2, allProjects.size());
        assertEquals("Orbit", allProjects.get(0).getName());
        assertEquals("Moon", allProjects.get(1).getName());
    }

    @Test
    void shouldReturnEmptyListWhenNoProjectsExist(){
        // Given
        List<Project> allProjects = service.findAll();

        // Then
        assertEquals(0, allProjects.size());
    }

    @Test
    void shouldIncrementIdForEachProjectCreated() {
        // Given
        Project p1 = new Project();
        p1.setName("Orbit");

        Project p2 = new Project();
        p2.setName("Moon");

        // When
        Project saved1 = service.create(p1);
        Project saved2 = service.create(p2);

        // Then
        assertEquals(1, saved1.getId());
        assertEquals(2, saved2.getId());
    }
}
