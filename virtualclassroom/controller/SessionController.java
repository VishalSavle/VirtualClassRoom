package ksolve.virtualclassroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ksolve.virtualclassroom.entity.Session;
import ksolve.virtualclassroom.service.SessionService;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    // Create a new session (chapter)
    @PostMapping
    public ResponseEntity<String> createSession(@RequestBody Session session) {
        return sessionService.createSession(session);
    }

    // Get all sessions
    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {
        return sessionService.getAllSessions();
    }

    // Get session by ID
    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        return sessionService.getSessionById(id);
    }

    // Update a session
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSession(@PathVariable Long id, @RequestBody Session session) {
        return sessionService.updateSession(id, session);
    }

    // Delete a session
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        return sessionService.deleteSession(id);
    }
}
