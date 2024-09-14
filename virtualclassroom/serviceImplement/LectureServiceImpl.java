package ksolve.virtualclassroom.serviceImplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ksolve.virtualclassroom.entity.Lecture;
import ksolve.virtualclassroom.repository.LectureRepository;
import ksolve.virtualclassroom.service.LectureService;

@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    @Override
    public ResponseEntity<String> createLecture(Lecture lectureEntity) {
        Optional<Lecture> existingLecture = lectureRepository.findById(lectureEntity.getId());
        
        if (existingLecture.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lecture with the given ID already exists.");
        } else {
            lectureRepository.save(lectureEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Lecture created successfully.");
        }
    }

    @Override
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> lectures = lectureRepository.findAll();
        if (lectures.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(lectures);
        }
        return ResponseEntity.ok(lectures);
    }

    @Override
    public ResponseEntity<Lecture> getLectureById(Long id) {
        Optional<Lecture> lectureEntity = lectureRepository.findById(id);
        if (lectureEntity.isPresent()) {
            return ResponseEntity.ok(lectureEntity.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<String> updateLecture(Long id, Lecture lectureEntity) {
        if (!lectureRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lecture not found.");
        }
        lectureEntity.setId(id);
        lectureRepository.save(lectureEntity);
        return ResponseEntity.ok("Lecture updated successfully.");
    }

    @Override
    public ResponseEntity<Void> deleteLecture(Long id) {
        if (!lectureRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        lectureRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}