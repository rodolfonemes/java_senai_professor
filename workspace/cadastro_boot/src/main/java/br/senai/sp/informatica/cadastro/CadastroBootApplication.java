package br.senai.sp.informatica.cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CadastroBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroBootApplication.class, args);
		
//		String senha = JOptionPane.showInputDialog("Informe a Senha");
//		System.out.println(new BCryptPasswordEncoder().encode(senha));
	}
}
