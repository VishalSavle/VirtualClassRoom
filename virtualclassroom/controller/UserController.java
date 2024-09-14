package ksolve.virtualclassroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ksolve.virtualclassroom.dto.RegisterDTO;
import ksolve.virtualclassroom.service.UserService;

@RestController
@RequestMapping("/userLogin/ksolve/api")
public class UserController {
	@Autowired
	public UserService userService;
	@PostMapping("/registerUser")
	public ResponseEntity<?>createUser(@RequestBody RegisterDTO dto){
		return userService.Registration(dto);	
	}
}
