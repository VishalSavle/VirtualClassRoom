package ksolve.virtualclassroom.serviceImplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ksolve.virtualclassroom.entity.Classroom;
import ksolve.virtualclassroom.repository.ClassroomRepository;
import ksolve.virtualclassroom.service.ClassroomService;

public class ClassServiceImpl implements ClassroomService{
	@Autowired
	private ClassroomRepository classroomRepository;

	@Override
	public ResponseEntity<String> createClass(Classroom classEntity) {
		  Optional<Classroom> existingClass = classroomRepository.findById(classEntity.getId());
	        
	        if (existingClass.isPresent()) {
	            // If the class already exists, return a BAD_REQUEST response
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                                 .body("Class Already exist"); // or provide an appropriate error message
	        } else {
	            // Save the new class and return a CREATED response
	            Classroom createdClass = classroomRepository.save(classEntity);
	            return ResponseEntity.status(HttpStatus.CREATED).body("Class is created");
	        }
	    }

	 @Override
	    public ResponseEntity<List<Classroom>> getAllClasses() {
	        List<Classroom> classes = classroomRepository.findAll();
	        if (classes.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(classes);
	        }
	        return ResponseEntity.ok(classes);
	    }

	    @Override
	    public ResponseEntity<String> getClassById(Long id) {
	        Optional<Classroom> classEntity = classroomRepository.findById(id);
	        if (classEntity.isPresent()) {
	            return ResponseEntity.status(HttpStatus.OK).body(null);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }

	    @Override
	    public ResponseEntity<String> updateClass(Long id, Classroom classEntity) {
	        if (!classroomRepository.existsById(id)) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found.");
	        }
	        classEntity.setId(id); // Ensure the ID is set for update
	        classroomRepository.save(classEntity);
	        return ResponseEntity.ok("Class updated successfully.");
	    }

	    @Override
	    public ResponseEntity<Void> deleteClass(Long id) {
	        if (!classroomRepository.existsById(id)) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	        classroomRepository.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }

	
}
