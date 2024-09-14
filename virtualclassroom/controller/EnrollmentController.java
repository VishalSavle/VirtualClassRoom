package ksolve.virtualclassroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ksolve.virtualclassroom.entity.Enrollment;
import ksolve.virtualclassroom.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Enroll a student
    @PostMapping
    public ResponseEntity<String> enrollStudent(@RequestBody Enrollment enrollment) {
        return enrollmentService.enrollStudent(enrollment);
    }

    // Get all enrollments
    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // Get enrollment by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    // Delete enrollment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        return enrollmentService.deleteEnrollment(id);
    }
}
