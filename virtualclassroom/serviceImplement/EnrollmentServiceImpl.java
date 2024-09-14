package ksolve.virtualclassroom.serviceImplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ksolve.virtualclassroom.entity.Enrollment;
import ksolve.virtualclassroom.repository.EnrollmentRepository;
import ksolve.virtualclassroom.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public ResponseEntity<String> enrollStudent(Enrollment enrollment) {
        // Check if student is already enrolled in the class
        Optional<Enrollment> existingEnrollment = enrollmentRepository.findByStudentIdAndClassId(
                enrollment.getUser().getId(), enrollment.getClassEntity().getId());
        
        if (existingEnrollment.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student is already enrolled in this class.");
        } else {
            enrollmentRepository.save(enrollment);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student enrolled successfully.");
        }
    }

    @Override
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        if (enrollments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(enrollments);
        }
        return ResponseEntity.ok(enrollments);
    }

    @Override
    public ResponseEntity<String> getEnrollmentById(Long id) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        if (enrollment.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<Void> deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        enrollmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
