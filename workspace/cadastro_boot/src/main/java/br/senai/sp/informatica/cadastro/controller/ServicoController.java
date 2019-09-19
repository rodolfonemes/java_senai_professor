package br.senai.sp.informatica.cadastro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.informatica.cadastro.component.JsonError;
import br.senai.sp.informatica.cadastro.model.Servico;
import br.senai.sp.informatica.cadastro.service.ServicoService;

@RestController
@RequestMapping("/api")
public class ServicoController {
	@Autowired
	private ServicoService servicoService;
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/salvaServico")
	public ResponseEntity<Object> salvarServico(@RequestBody @Valid Servico servico, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.unprocessableEntity()
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(JsonError.build(result));
		} else {
			servicoService.salvar(servico);
			return ResponseEntity.ok().build();
		}
	}
	
	@GetMapping("/listaServico")
	public ResponseEntity<List<Servico>> listaServico() {
		return ResponseEntity.ok(servicoService.getServicos());
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/removeServico/{idServico}")
	public ResponseEntity<Object> removeServico(@PathVariable("idServico") int idServico) {
		if(servicoService.removeServico(idServico)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

}
