package com.marquinhos.dev.orbit.service;

import com.marquinhos.dev.orbit.model.Project;
import com.marquinhos.dev.orbit.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepository repository;

    @InjectMocks
    private ProjectService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateProjectWhenNameIsValid() {
        Project project = new Project();
        project.setName("Orbit");

        Project saved = new Project();
        saved.setId(1);
        saved.setName("Orbit");

        when(repository.save(any(Project.class))).thenReturn(saved);

        Project result = service.create(project);

        assertNotNull(result.getId());
        assertEquals("Orbit", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        Project project = new Project();

        assertThrows(IllegalArgumentException.class, () -> service.create(project));
        verify(repository, never()).save(any());
    }

    @Test
    void shouldFindById() {
        Project project = new Project();
        project.setId(1);
        project.setName("Orbit");

        when(repository.findById(1)).thenReturn(Optional.of(project));

        Optional<Project> result = service.findById(1);

        assertTrue(result.isPresent());
        assertEquals("Orbit", result.get().getName());
    }

    @Test
    void shouldDeleteProjectWhenExists() {
        when(repository.existsById(1)).thenReturn(true);

        service.delete(1);

        verify(repository).deleteById(1);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingProject() {
        when(repository.existsById(99)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> service.delete(99));
    }
}
