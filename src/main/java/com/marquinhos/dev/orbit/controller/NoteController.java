package com.marquinhos.dev.orbit.controller;

import com.marquinhos.dev.orbit.model.Note;
import com.marquinhos.dev.orbit.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<Note>> findAll(){
        return ResponseEntity.ok(noteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> findById(@PathVariable Integer id){
        return noteService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Note>> findByProjectId(@PathVariable Integer projectId){
        return ResponseEntity.ok(noteService.findByProjectId(projectId));
    }

    @PostMapping("/project/{projectId}")
    public ResponseEntity<Note> save(@PathVariable Integer projectId, @RequestBody Note note){
        return ResponseEntity.ok(noteService.save(projectId, note));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
