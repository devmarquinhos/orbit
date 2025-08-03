package com.marquinhos.dev.orbit.service;

import com.marquinhos.dev.orbit.model.Project;
import org.junit.jupiter.api.Test;
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
}
