package br.senai.sp.informatica.cadastro.model.validacao;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SenhaValidator implements ConstraintValidator<Senha, String> {

	BiFunction<String, Predicate<Integer>, Boolean> regra = (texto, condicao) ->
			// Para cada letra deste texto, faça...
			texto.chars()
			// Separe a letra que atender a condição
			.filter(letra -> condicao.test(letra))
			// Separe a 1ª letra que encontrar
			.findAny()
			// Então, achou?
			.isPresent();
			
	
	@Override
	public boolean isValid(String senha, ConstraintValidatorContext context) {
		// A Senha deve ter no mínimo 8 caracteres
		return senha.length() >= 8 &&
			// deve ter pelo menos um símbolo (# ou & ou $ ou $)
			regra.apply(senha, letra -> letra == '#' || letra == '&' || letra == '$' || letra == '%') &&
			// deve ter pelo menos uma letra maiúscula
			regra.apply(senha, Character::isUpperCase) &&
			// e ter pelo menos um digito
			regra.apply(senha, Character::isDigit);
	}

}
