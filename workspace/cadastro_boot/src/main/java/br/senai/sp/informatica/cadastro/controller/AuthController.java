package br.senai.sp.informatica.cadastro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.cadastro.component.JwtTokenProvider;
import br.senai.sp.informatica.cadastro.model.valueobject.JwtAuthenticationResponse;
import br.senai.sp.informatica.cadastro.model.valueobject.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> autenticaUsuario(@RequestBody @Valid LoginRequest login) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						login.getUsername(), login.getPassword() ) );
			
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		// Criar o Token de Autenticação
		String jwt = tokenProvider.generateToken(auth);

		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
}