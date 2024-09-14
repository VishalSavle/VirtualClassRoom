package ksolve.virtualclassroom.serviceImplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ksolve.virtualclassroom.entity.Session;
import ksolve.virtualclassroom.repository.SessionRepository;
import ksolve.virtualclassroom.service.SessionService;

public class SessionServiceImpl implements SessionService{
	@Autowired
    private SessionRepository sessionRepository;

	@Override
	public ResponseEntity<String> createSession(Session sessionEntity) {
		  Optional<Session> existingSession = sessionRepository.findById(sessionEntity.getId());
	        
	        if (existingSession.isPresent()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Session with the given ID already exists.");
	        } else {
	            sessionRepository.save(sessionEntity);
	            return ResponseEntity.status(HttpStatus.CREATED).body("Session created successfully.");
	        }
	    }
	@Override
	public ResponseEntity<List<Session>> getAllSessions() {
		  List<Session> sessions = sessionRepository.findAll();
	        if (sessions.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sessions);
	        }
	        return ResponseEntity.ok(sessions);
	    }

	@Override
	public ResponseEntity<Session> getSessionById(Long id) {
		 Optional<Session> sessionEntity = sessionRepository.findById(id);
	        if (sessionEntity.isPresent()) {
	            return ResponseEntity.ok(sessionEntity.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	}

	@Override
	public ResponseEntity<String> updateSession(Long id, Session sessionEntity) {
		  if (!sessionRepository.existsById(id)) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Session not found.");
	        }
	        sessionEntity.setId(id);
	        sessionRepository.save(sessionEntity);
	        return ResponseEntity.ok("Session updated successfully.");
	    }

	@Override
	public ResponseEntity<Void> deleteSession(Long id) {
		 if (!sessionRepository.existsById(id)) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	        sessionRepository.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }

}
