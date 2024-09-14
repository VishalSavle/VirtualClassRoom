package ksolve.virtualclassroom.serviceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ksolve.virtualclassroom.dto.LoginDTO;
import ksolve.virtualclassroom.jwt.JwtUtil;
import ksolve.virtualclassroom.repository.UserRepository;
import ksolve.virtualclassroom.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public ResponseEntity<String> UserLogin(LoginDTO dto) {
	    try {
	        // Load user details from the database
	        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getEmail());

	        // Print the stored (encoded) password for debugging purposes
	        System.out.println("Stored Password (Encoded): " + userDetails.getPassword());

	        // Print the raw password entered by the user (for comparison)
	        System.out.println("Entered Password (Raw): " + dto.getPassword());

	        // Authenticate user using AuthenticationManager
	        UsernamePasswordAuthenticationToken authenticationToken =
	                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

	        authenticationManager.authenticate(authenticationToken);

	        // Generate JWT token upon successful authentication
	        String token = jwtUtil.generateToken(dto.getEmail(), userDetails.getAuthorities());

	        // Return JWT token in response
	        return ResponseEntity.ok(token);

	    } catch (BadCredentialsException e) {
	        System.out.println("Invalid email or password for email: " + dto.getEmail());
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	    } catch (Exception e) {
	        // Log the full stack trace to find out what the real issue is
	        e.printStackTrace();  // This will print the full error in the console
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
	    }
	}

}