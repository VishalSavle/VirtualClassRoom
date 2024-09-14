package ksolve.virtualclassroom.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ksolve.virtualclassroom.entity.Session;

public interface SessionService {
	 ResponseEntity<String> createSession(Session sessionEntity);
	 ResponseEntity<List<Session>> getAllSessions();
	    ResponseEntity<Session> getSessionById(Long id);
	    ResponseEntity<String> updateSession(Long id, Session sessionEntity);
	    ResponseEntity<Void> deleteSession(Long id);
}
