package ksolve.virtualclassroom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ksolve.virtualclassroom.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
