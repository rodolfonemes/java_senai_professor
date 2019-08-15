package br.senai.sp.informatica.objeto;

import lombok.Data;

@Data
public class Cliente {
	private String nome;
	private String email;
	private int idade;
	
	@Override
	public String toString() {
		return "nome: " + nome + " email: " + email + " idade: " + idade;
	}
}
