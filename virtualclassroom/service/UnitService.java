package ksolve.virtualclassroom.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ksolve.virtualclassroom.entity.Unit;

public interface UnitService {
	   ResponseEntity<String> createUnit(Unit unitEntity);
	    ResponseEntity<List<Unit>> getAllUnits();
	    ResponseEntity<Unit> getUnitById(Long id);
	    ResponseEntity<String> updateUnit(Long id, Unit unitEntity);
	    ResponseEntity<Void> deleteUnit(Long id);
}