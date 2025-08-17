package com.marquinhos.dev.orbit.service;

import com.marquinhos.dev.orbit.model.Note;
import com.marquinhos.dev.orbit.model.Project;
import com.marquinhos.dev.orbit.repository.NoteRepository;
import com.marquinhos.dev.orbit.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class NoteServiceTest {

    private NoteRepository noteRepository;
    private ProjectRepository projectRepository;
    private NoteService noteService;

    @BeforeEach
    void setup() {
        noteRepository = Mockito.mock(NoteRepository.class);
        projectRepository = Mockito.mock(ProjectRepository.class);
        noteService = new NoteService(noteRepository, projectRepository);
    }

    @Test
    void shouldSaveNoteWhenProjectExists() {
        Project project = Project.builder()
                .id(1)
                .name("Orbit")
                .description("Agregador de projetos")
                .build();

        Note note = Note.builder()
                .id(1)
                .content("Primeira nota")
                .build();

        Mockito.when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        Mockito.when(noteRepository.save(any(Note.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Note saved = noteService.save(1, note);

        assertEquals("Primeira nota", saved.getContent());
        assertEquals(project, saved.getProject());
    }
}
