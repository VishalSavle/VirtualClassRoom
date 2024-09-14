package ksolve.virtualclassroom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ksolve.virtualclassroom.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	Optional<Enrollment> findByStudentIdAndClassId(Long id, Long id2);
}