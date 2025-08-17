package com.marquinhos.dev.orbit.repository;

import com.marquinhos.dev.orbit.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Integer> {
    List<Note> findByProjectId(Integer projectId);
}
