package br.senai.sp.informatica.cadastro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senai.sp.informatica.cadastro.component.JsonError;
import br.senai.sp.informatica.cadastro.model.Usuario;
import br.senai.sp.informatica.cadastro.service.UsuarioService;

@Controller
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/salvaUsuario")
	public ResponseEntity<Object> salvaUsuario(@RequestBody @Valid Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.unprocessableEntity()
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(JsonError.build(result));
		} else {
			usuarioService.salvar(usuario);
			return ResponseEntity.ok().build();
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/listaUsuario")
	public ResponseEntity<List<Usuario>> listaUsuario() {
		return ResponseEntity.ok(usuarioService.getUsuarios());
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/editaUsuario/{nome}")
	public ResponseEntity<Object> editaUsuario(@PathVariable("nome") String nome) {
		Usuario usuario = usuarioService.getUsuario(nome);
		
		if(usuario != null) {
			usuario.setOld_nome(usuario.getNome());
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/removeUsuario/{nome}")
	public ResponseEntity<Object> removeUsuario(@PathVariable("nome") String nome) {
		if(usuarioService.removeUsuario(nome)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@RequestMapping("/leAutorizacoes/{nome}")
	public ResponseEntity<GrantedAuthority> getAutorizacoes(@PathVariable("nome") String nome) {
		return ResponseEntity.ok(usuarioService.getAutorizacoes(nome));
	}
}
