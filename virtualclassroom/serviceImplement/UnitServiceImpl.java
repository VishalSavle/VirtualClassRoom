package ksolve.virtualclassroom.serviceImplement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ksolve.virtualclassroom.entity.Unit;
import ksolve.virtualclassroom.repository.UnitRepository;
import ksolve.virtualclassroom.service.UnitService;

public class UnitServiceImpl implements UnitService{
	@Autowired
    private UnitRepository unitRepository;
	@Override
	public ResponseEntity<String> createUnit(Unit unit) {
		   Optional<Unit> existingUnit = unitRepository.findById(unit.getId());
	        
	        if (existingUnit.isPresent()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unit with the given ID already exists.");
	        } else {
	            unitRepository.save(unit);
	            return ResponseEntity.status(HttpStatus.CREATED).body("Unit created successfully.");
	        }
	}

	@Override
	public ResponseEntity<List<Unit>> getAllUnits() {
		   List<Unit> units = unitRepository.findAll();
	        if (units.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(units);
	        }
	        return ResponseEntity.ok(units);
	    }

	@Override
	public ResponseEntity<Unit> getUnitById(Long id) {
		   Optional<Unit> unitEntity = unitRepository.findById(id);
	        if (unitEntity.isPresent()) {
	            return ResponseEntity.ok(unitEntity.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	}

	@Override
	public ResponseEntity<String> updateUnit(Long id, Unit unit) {
		   if (!unitRepository.existsById(id)) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unit not found.");
	        }
	        unit.setId(id);
	        unitRepository.save(unit);
	        return ResponseEntity.ok("Unit updated successfully.");
	}

	@Override
	public ResponseEntity<Void> deleteUnit(Long id) {
		 if (!unitRepository.existsById(id)) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	        unitRepository.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }

}
