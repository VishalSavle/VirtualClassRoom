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

import ksolve.virtualclassroom.entity.Unit;
import ksolve.virtualclassroom.service.UnitService;

@RestController
@RequestMapping("/api/units")
public class UnitController {

    @Autowired
    private UnitService unitService;

    // Create a new unit (book)
    @PostMapping
    public ResponseEntity<String> createUnit(@RequestBody Unit unit) {
        return unitService.createUnit(unit);
    }

    // Get all units
    @GetMapping
    public ResponseEntity<List<Unit>> getAllUnits() {
        return unitService.getAllUnits();
    }

    // Get unit by ID
    @GetMapping("/{id}")
    public ResponseEntity<Unit> getUnitById(@PathVariable Long id) {
        return unitService.getUnitById(id);
    }

    // Update a unit
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUnit(@PathVariable Long id, @RequestBody Unit unit) {
        return unitService.updateUnit(id, unit);
    }

    // Delete a unit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        return unitService.deleteUnit(id);
    }
}
