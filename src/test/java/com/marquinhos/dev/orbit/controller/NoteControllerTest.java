package com.marquinhos.dev.orbit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marquinhos.dev.orbit.model.Note;
import com.marquinhos.dev.orbit.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnNoteById() throws Exception {
        Note note = Note.builder().id(1).content("Nota teste").build();
        when(noteService.findById(1)).thenReturn(Optional.of(note));

        mockMvc.perform(get("/notes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Nota teste"));
    }

    @Test
    void shouldCreateNote() throws Exception {
        Note note = Note.builder().id(1).content("Nova nota").build();
        when(noteService.save(1, note)).thenReturn(note);

        mockMvc.perform(post("/notes/project/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(note)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Nova nota"));
    }
}
