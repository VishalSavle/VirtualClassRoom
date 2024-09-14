package ksolve.virtualclassroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ksolve.virtualclassroom.entity.Classroom;
import ksolve.virtualclassroom.service.ClassroomService;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    // Create a new classroom
    @PostMapping
    public ResponseEntity<String> createClassroom(@RequestBody Classroom classroom) {
        return classroomService.createClass(classroom);
    }

    // Get all classrooms
    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        return classroomService.getAllClasses();
    }

    // Get a classroom by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getClassroomById(@PathVariable Long id) {
        return classroomService.getClassById(id);
    }

    // Update a classroom
    @PutMapping("/{id}")
    public ResponseEntity<String> updateClassroom(@PathVariable Long id, @RequestBody Classroom classroom) {
        return classroomService.updateClass(id, classroom);
    }

    // Delete a classroom
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        return classroomService.deleteClass(id);
    }
}
