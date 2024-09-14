package ksolve.virtualclassroom.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ksolve.virtualclassroom.entity.Enrollment;

public interface EnrollmentService {
	 ResponseEntity<String> enrollStudent(Enrollment enrollment);
	 ResponseEntity<List<Enrollment>> getAllEnrollments();
	    ResponseEntity<String> getEnrollmentById(Long id);
	    ResponseEntity<Void> deleteEnrollment(Long id);
}
