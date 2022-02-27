package hu.webuni.hr.atold.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.atold.dto.LoginDto;

@RestController
public class JWTLoginController {
	
	@Autowired
	AuthenticationManager autheticationManager;
	
	@Autowired
	JWTService jwtService;
	
	
	@PostMapping("/api/login")
	public String login(@RequestBody LoginDto login) {
		
		Authentication authentication = autheticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		return jwtService.creatJwtToken((UserDetails)authentication.getPrincipal());
	}

}
