package ksolve.virtualclassroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ksolve.virtualclassroom.entity.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
