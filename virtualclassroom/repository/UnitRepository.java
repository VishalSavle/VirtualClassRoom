package ksolve.virtualclassroom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ksolve.virtualclassroom.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long>{
	Optional<Unit>findById(Long id);
}
