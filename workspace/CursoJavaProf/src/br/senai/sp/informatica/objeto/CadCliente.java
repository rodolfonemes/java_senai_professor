package br.senai.sp.informatica.objeto;

import javax.swing.JOptionPane;

public class CadCliente {
	public static void main(String[] args) {		
		Cliente novo = new Cliente();
		String temp = JOptionPane.showInputDialog("Informe o Nome");
		novo.setNome(temp);
		
		temp = JOptionPane.showInputDialog("Informe o Email");
		novo.setEmail(temp);
		
		temp = JOptionPane.showInputDialog("Informe a Idade");
		novo.setIdade(Integer.parseInt(temp));
		
		System.out.println(novo.getNome());
	}
}
