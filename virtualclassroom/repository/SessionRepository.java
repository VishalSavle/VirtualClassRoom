package ksolve.virtualclassroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ksolve.virtualclassroom.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
