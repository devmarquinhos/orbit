package com.marquinhos.dev.orbit.controller;

import com.marquinhos.dev.orbit.model.Note;
import com.marquinhos.dev.orbit.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Note>> findByProjectId(@PathVariable("projectId") Integer projectId){
        return ResponseEntity.ok(noteService.findByProjectId(projectId));
    }

    @PostMapping("/project/{projectId}")
    public ResponseEntity<Note> save(@PathVariable("projectId") Integer projectId, @RequestBody Note note){
        Note saved = noteService.save(projectId, note);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
