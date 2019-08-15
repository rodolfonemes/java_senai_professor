package br.senai.sp.informatica.lib;

import java.util.Locale;

import javax.swing.JOptionPane;

public class InputUtil {
	private InputUtil() {}
	
	/**
	 * Retorna a instância de CurrencyFormat o formatador de números
	 * reais para a região Brasileira
	 * @return a instância de CurrencyFormat
	 */
	public static CurrencyFormat getFormatador() {
		return new CurrencyFormat();
	}
	
	/**
	 * Implementa método de leitura de Strings
	 * @param args lista de arqumentos a serem utilizados como prompt
	 * @return o texto lido
	 */
	public static String leTexto(Object ... args) {
		String txt = "";

		for (Object arg : args) {
			if (arg instanceof String) {
				txt += String.format("%s", arg);
			} else if (arg instanceof Integer) {
				txt += String.format("%d", arg);
			} else if (arg instanceof Double) {
				txt += String.format(new Locale("pt","BR"), "%,.2f", arg);
			}
		}

		return JOptionPane.showInputDialog(txt);
	}
	
	private static Number leNumber(Class<?> type, Object... args) {
		Number ret = 0;
		while (true) {
			try {
				if(type.equals(Integer.class))
					ret = Integer.parseInt(leTexto(args));
				else
					ret = getFormatador().parse(leTexto(args));
				break;
			} catch (NumberFormatException ex) {
				escrevaErro("Número inválido");
			} catch (Error ex) {
				escrevaErro("Valor inválido");
			}
		}
		return ret;
	}
	
	/**
	 * Implementa método de leitura de Integers
	 * @param args lista de arqumentos a serem utilizados como prompt
	 * @return o inteiro lido
	 */
	public static Integer leInteiro(Object... args) {
		int ret = leNumber(Integer.class, args).intValue();
		return ret;
	}
	
	/**
	 * Implementa método de leitura de Doubles
	 * @param args lista de arqumentos a serem utilizados como prompt
	 * @return o double lido
	 */
	public static Double leReal(Object... args) {
		double ret = leNumber(Double.class, args).doubleValue();
		return ret;
	}
	
	/**
	 * Implementa método de escrita sem salto de linha ao final
	 * @param args lista de arqumentos a serem apresentados
	 */
	public static void escreva(Object ... args) {
		
		String txt = "";
		
		for (Object arg : args) {
			if (arg instanceof String) {
				txt += String.format("%s", arg);
			} else if (arg instanceof Character) {
				txt += String.format("%c", arg);
			} else if (arg instanceof Integer) {
				txt += String.format(new Locale("pt","BR"),"%d", arg);
			} else if (arg instanceof Double) {
				txt += String.format(new Locale("pt","BR"),"%,.2f", arg);
			}
		}

		JOptionPane.showMessageDialog(null, txt);
	}

	private static void escrevaErro(String txt) {
		JOptionPane.showMessageDialog(null, txt, "Erro", JOptionPane.ERROR_MESSAGE);
	}

}
