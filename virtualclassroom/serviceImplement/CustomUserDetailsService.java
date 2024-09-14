package ksolve.virtualclassroom.serviceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ksolve.virtualclassroom.entity.User;
import ksolve.virtualclassroom.repository.UserRepository;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Assuming you're using a repository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by username (email in your case)
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Get the user's role
        String role = user.getRole();

        // Convert the role to a GrantedAuthority
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

        // Return UserDetails with username, password, and authority
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(authority)  // Use singletonList to avoid the error
        );
    }
}
