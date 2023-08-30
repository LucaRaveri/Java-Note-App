package com.example.application.backend.service;

import com.example.application.backend.exception.NoteNotFoundException;
import com.example.application.backend.model.entity.Note;
import com.example.application.backend.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note findById(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if(note.isEmpty()) {
            throw new NoteNotFoundException(id);
        } else {
            return note.get();
        }
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Object createNote(Note note) {
        return noteRepository.save(note);
    }

    public Object save(Note note) {
        return noteRepository.save(note);
    }

    public int count() {
        return (int) noteRepository.count();
    }

    public Object updateNote(Note request) {
        Optional<Note> note = noteRepository.findById(request.getId());
        if(note.isEmpty()) {
            throw new NoteNotFoundException(request.getId());
        } else {
            Note.update(note.get(), request);
            noteRepository.save(note.get());
            return request;
        }
    }

    public void deleteNote(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if(note.isEmpty()) {
            throw new NoteNotFoundException(id);
        } else {
            noteRepository.delete(note.get());
        }
    }

    public List<Note> findAllByCreatedBy(String username) {
        return noteRepository.findAllByCreatedBy(username);
    }
}
