package pl.edu.agh.currency.beans;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(ICurrencyConverter.class)
public class CurrencyConverterBean implements ICurrencyConverter {

	private static Map<String, Map<String, BigDecimal>> exchangeRates = new HashMap<>();
	static {
	    Map<String, BigDecimal> plnExchangeRates = new HashMap<>();
	    plnExchangeRates.put("PLN", new BigDecimal("1"));
	    plnExchangeRates.put("USD", new BigDecimal("0.264449"));
	    plnExchangeRates.put("EUR", new BigDecimal("0.219703"));
	    exchangeRates.put("PLN", plnExchangeRates);

	    Map<String, BigDecimal> usdExchangeRates = new HashMap<>();
	    usdExchangeRates.put("USD", new BigDecimal("1"));
	    usdExchangeRates.put("PLN", new BigDecimal("3.729988"));
	    usdExchangeRates.put("EUR", new BigDecimal("0.823413"));
	    exchangeRates.put("USD", usdExchangeRates);
	    
	    Map<String, BigDecimal> eurExchangeRates = new HashMap<>();
	    eurExchangeRates.put("EUR", new BigDecimal("1"));
	    eurExchangeRates.put("USD", new BigDecimal("1.214411"));
	    eurExchangeRates.put("PLN", new BigDecimal("4.529175"));
	    exchangeRates.put("EUR", eurExchangeRates);
	}
	
	@Override
	public BigDecimal convert(BigDecimal amount, String fromCurrency, String toCurrency) {
		BigDecimal rate = exchangeRates.get(fromCurrency).get(toCurrency);
		return amount.multiply(rate);
	}

	@Override
	public Map<String, BigDecimal> convertToAll(BigDecimal amount, String fromCurrency) {
		Map<String, BigDecimal> result = new HashMap<>();
		
		String currency;
		BigDecimal rate;
		
		Map<String, BigDecimal> fromCurrencyRates = exchangeRates.get(fromCurrency); 
		for (Map.Entry<String, BigDecimal> entry:fromCurrencyRates.entrySet()) {
			currency = entry.getKey();
			rate = entry.getValue();
			result.put(currency, amount.multiply(rate));
		}
		
		return result;
	}
}
