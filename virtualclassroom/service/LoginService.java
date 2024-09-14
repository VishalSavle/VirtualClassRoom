package ksolve.virtualclassroom.service;

import org.springframework.http.ResponseEntity;

import ksolve.virtualclassroom.dto.LoginDTO;

public interface LoginService {
	ResponseEntity<String>UserLogin(LoginDTO dto);
	 
	
	
}
