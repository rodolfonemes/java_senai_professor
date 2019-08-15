package br.senai.sp.informatica.lib;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class CurrencyFormat extends DecimalFormat {
	private static final long serialVersionUID = -554614047140445496L;
	
	public CurrencyFormat() {
		super("#,##0.00;-#,##0.00");
		super.setMaximumFractionDigits(2);
		super.setMinimumFractionDigits(2);
	}

	@Override
	public Double parse(String value) {
	    final ParsePosition pos = new ParsePosition(0);
	    final Number parsedNumber = super.parse(value, pos);
	    
	    if (pos.getErrorIndex() >= 0 || pos.getIndex() != value.length() || parsedNumber == null) {
	        throw new Error("Valor inválido na posição: " + pos.getErrorIndex());
	    }
	    return parsedNumber.doubleValue();
	}
}
