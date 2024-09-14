package ksolve.virtualclassroom.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ksolve.virtualclassroom.entity.Lecture;

public interface LectureService {
	 ResponseEntity<String> createLecture(Lecture lectureEntity);
	    ResponseEntity<List<Lecture>> getAllLectures();
	    ResponseEntity<Lecture> getLectureById(Long id);
	    ResponseEntity<String> updateLecture(Long id, Lecture lectureEntity);
	    ResponseEntity<Void> deleteLecture(Long id);
}
