package ksolve.virtualclassroom.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ksolve.virtualclassroom.entity.Classroom;

public interface ClassroomService {
	 ResponseEntity<String> createClass(Classroom classEntity);
	 ResponseEntity<List<Classroom>> getAllClasses();
	    ResponseEntity<String> getClassById(Long id);
	    ResponseEntity<String> updateClass(Long id, Classroom classEntity);
	    ResponseEntity<Void> deleteClass(Long id);
}
