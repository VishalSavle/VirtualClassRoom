package ksolve.virtualclassroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ksolve.virtualclassroom.dto.LoginDTO;
import ksolve.virtualclassroom.service.LoginService;

@RestController
@RequestMapping("/ksolve/api")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return loginService.UserLogin(loginDTO);
    }	
}
