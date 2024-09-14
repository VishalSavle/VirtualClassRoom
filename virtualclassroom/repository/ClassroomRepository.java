package ksolve.virtualclassroom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ksolve.virtualclassroom.entity.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long>{
	Optional<Classroom>findByClassId(Long id);
}
