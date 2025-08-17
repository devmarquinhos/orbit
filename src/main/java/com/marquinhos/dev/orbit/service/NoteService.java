package com.marquinhos.dev.orbit.service;

import com.marquinhos.dev.orbit.model.Note;
import com.marquinhos.dev.orbit.model.Project;
import com.marquinhos.dev.orbit.repository.NoteRepository;
import com.marquinhos.dev.orbit.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final ProjectRepository projectRepository;

    public NoteService(NoteRepository noteRepository, ProjectRepository projectRepository) {
        this.noteRepository = noteRepository;
        this.projectRepository = projectRepository;
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Optional<Note> findById(Integer id) {
        return noteRepository.findById(id);
    }

    public Note save(Integer projectId, Note note) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found." + projectId));
        note.setProject(project);
        return noteRepository.save(note);
    }

    public void delete(Integer id){
        noteRepository.deleteById(id);
    }

    public List<Note> findByProjectId(Integer projectId){
        return noteRepository.findByProjectId(projectId);
    }
}
