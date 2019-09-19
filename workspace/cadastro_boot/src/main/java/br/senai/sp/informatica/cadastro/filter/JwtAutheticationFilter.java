package br.senai.sp.informatica.cadastro.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.senai.sp.informatica.cadastro.component.JwtTokenProvider;
import br.senai.sp.informatica.cadastro.model.Usuario;
import br.senai.sp.informatica.cadastro.service.UsuarioService;

public class JwtAutheticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private UsuarioService usuarioService;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAutheticationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			// Retira do Header HTTP o Token de autenticação
			String jwt = getJwtFromRequest(request);
			
			// Valida o Token
			if(StringUtils.hasLength(jwt) && tokenProvider.validateToken(jwt)) {
				// Obtém o UserID a partir do Token
				String userId = tokenProvider.getUserIdFromJWT(jwt);

				// Localiza os dados do Usuário do Banco de Dados
				Usuario usuario = usuarioService.getUsuario(userId);
				
				// Cria o Token de Autenticação para o Ambiente de Segurança Web
				UsernamePasswordAuthenticationToken autenticacao = 
						new UsernamePasswordAuthenticationToken(
								usuario, null, Collections.singletonList(  // <==== Faltou o "null"
										usuarioService.getAutorizacoes(userId) ));
				
				// Registra a URL da Requisição Web no Token de Autorização
				autenticacao.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request));
				// Autentica o Usuário no Ambiente de Segurança Web
				SecurityContextHolder.getContext().setAuthentication(autenticacao);
			}
		} catch (Exception erro) {
			logger.error("Não foi possível registrar a Autenticação", erro);
		}
		
		filterChain.doFilter(request, response); 						// <====== Faltou esta linha
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		// Obtém o Token do Cabeçálho HTTP
		String bearerToken = request.getHeader("Authorization");

		// Verifica se existe a informação de autenticação
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			// Retorna somente o Token excluindo o prefixo "Bearer "
			return bearerToken.substring(7, bearerToken.length());
		}
		
		return null;
	}
}











