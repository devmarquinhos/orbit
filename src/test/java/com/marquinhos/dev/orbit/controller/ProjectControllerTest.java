package com.marquinhos.dev.orbit.controller;

import com.marquinhos.dev.orbit.model.Project;
import com.marquinhos.dev.orbit.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    private ProjectController projectController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        projectController = new ProjectController(projectService);

        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    void shouldCreateProjectAndReturn201() throws Exception {
        Project projectSaved = new Project();
        projectSaved.setId(1);
        projectSaved.setName("Orbit");

        when(projectService.create(any(Project.class))).thenReturn(projectSaved);

        String jsonInput = """
            {
                "name": "Orbit"
            }
        """;

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isCreated())                // Verifica HTTP 201 Created
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verifica que a resposta é JSON
                .andExpect(jsonPath("$.id").value(1))           // Verifica o id retornado
                .andExpect(jsonPath("$.name").value("Orbit"));  // Verifica o nome retornado
    }

    @Test
    void shouldReturnListOfProjects() throws Exception {
        // Given
        Project p1 = new Project();
        p1.setId(1);
        p1.setName("Orbit");

        Project p2 = new Project();
        p2.setId(2);
        p2.setName("Moon");

        when(projectService.findAll()).thenReturn(List.of(p1, p2));

        // When & Then
        mockMvc.perform(get("/projects")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Orbit"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Moon"));
    }

    @Test
    void shouldReturnProjectById() throws Exception {
        Project project = new Project();
        project.setId(1);
        project.setName("Orbit");

        when(projectService.findById(1)).thenReturn(Optional.of(project));

        mockMvc.perform(get("/projects/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Orbit"));
    }

    @Test
    void shouldReturn404WhenProjectNotFound() throws Exception {
        when(projectService.findById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/projects/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteProject() throws Exception {
        doNothing().when(projectService).delete(1);

        mockMvc.perform(delete("/projects/1"))
                .andExpect(status().isNoContent());
    }
}
