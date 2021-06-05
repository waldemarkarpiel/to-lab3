package pl.edu.agh.currency.beans;

import java.math.BigDecimal;
import java.util.Map;

public interface ICurrencyConverter {
	
	BigDecimal convert(BigDecimal amount, String fromCurrency, String toCurrency);

	Map<String, BigDecimal> convertToAll(BigDecimal amount, String fromCurrency);
}
