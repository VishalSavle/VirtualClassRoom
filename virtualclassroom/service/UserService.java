package ksolve.virtualclassroom.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import ksolve.virtualclassroom.dto.RegisterDTO;

public interface UserService {
	ResponseEntity<String> Registration(RegisterDTO dto);

    ResponseEntity<String> getUserById(Long id);

    ResponseEntity<List<String>> getAllUsers();

    ResponseEntity<String> updateUser(long id, RegisterDTO dto);

    ResponseEntity<String> DeleteUser(Long id);
    ResponseEntity<String> getUserByEmail(String email);

}
