package ksolve.virtualclassroom.serviceImplement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ksolve.virtualclassroom.dto.RegisterDTO;
import ksolve.virtualclassroom.entity.User;
import ksolve.virtualclassroom.repository.UserRepository;
import ksolve.virtualclassroom.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public ResponseEntity<String> Registration(RegisterDTO dto) {
		 try {
		        Optional<User> UserEmail = userRepository.findByEmail(dto.getEmail());
		        if (UserEmail.isPresent()) {
		            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
		        }

		        // Create a new user entity
		        User user = new User();
		        user.setFirstName(dto.getFirstName());
		        user.setLastName(dto.getLastName());
		        user.setEmail(dto.getEmail());
		        user.setMobile(dto.getMobile());

		        // Encode the password before saving it to the database
		        String encodedPassword = passwordEncoder.encode(dto.getPassword());
		        user.setPassword(encodedPassword);

		        // Save the user
		        userRepository.save(user);
				/*
				 * String roleMessage; switch (dto.getRole().toUpperCase()) { case "ADMIN":
				 * roleMessage = "Admin account created successfully"; break; case "STUDENT":
				 * roleMessage = "Student account created successfully"; break; case
				 * "INSTRUCTOR": roleMessage = "Instructor account created successfully"; break;
				 * default: roleMessage = "User created successfully"; break; }
				 */
				
			return ResponseEntity.status(HttpStatus.CREATED).body("Employee created Successfully");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Employee Already Exist");
		}
	}

	@Override
	public ResponseEntity<String> getUserById(Long id) {
		 Optional<User> user = userRepository.findById(id);
	        if (user.isPresent()) {
	            return ResponseEntity.ok(user.get().toString());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
	        }
	}

	@Override
	public ResponseEntity<List<String>> getAllUsers() {
		  List<User> users = userRepository.findAll();
	        List<String> userDtos = users.stream()
	                .map(user -> user.getFirstName() + " " + user.getLastName())
	                .collect(Collectors.toList());
	        return ResponseEntity.ok(userDtos);
	}

	@Override
	public ResponseEntity<String> updateUser(long id, RegisterDTO dto) {
		  Optional<User> existingUser = userRepository.findById(id);
	        if (existingUser.isPresent()) {
	            User user = existingUser.get();
	            user.setFirstName(dto.getFirstName());
	            user.setLastName(dto.getLastName());
	            user.setEmail(dto.getEmail());
	            user.setMobile(dto.getMobile());
	            user.setPassword(passwordEncoder.encode(dto.getPassword()));
	            user.setRole(dto.getRole());

	            userRepository.save(user);
	            return ResponseEntity.ok("User updated successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
	        }
	}

	@Override
	public ResponseEntity<String> DeleteUser(Long id) {
		 Optional<User> existingUser = userRepository.findById(id);
	        if (existingUser.isPresent()) {
	            userRepository.deleteById(id);
	            return ResponseEntity.ok("User deleted successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
	        }
	}

	@Override
	public ResponseEntity<String> getUserByEmail(String email) {
		 Optional<User> user = userRepository.findByEmail(email);
	        if (user.isPresent()) {
	            return ResponseEntity.ok(user.get().toString());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: " + email);
	        }
	}

}
