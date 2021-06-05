package pl.edu.agh.currency.app;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import pl.edu.agh.currency.beans.ICurrencyConverter;

import java.math.BigDecimal;

public class CurrencyApp {
	public static void main(String[] args) {
		try {
			Context initialContext = createInitialContext();

			ICurrencyConverter currencyConverter = (ICurrencyConverter) initialContext.lookup("ejb:/CurrencyService//CurrencyConverterBean!pl.edu.agh.currency.beans.ICurrencyConverter");
			
			BigDecimal plnAmount = new BigDecimal("10");
			System.out.println("Oryginalna kwota w PLN: " + plnAmount);
			System.out.println("Kwota przeliczona na USD: " + currencyConverter.convert(plnAmount, "PLN", "USD"));
			System.out.println("Kwota przeliczona na EUR: " + currencyConverter.convert(plnAmount, "PLN", "EUR"));
			System.out.println("Kwota przeliczona na wszytkie znane waluty: " + currencyConverter.convertToAll(plnAmount, "PLN"));
			
			
			BigDecimal usdAmount = new BigDecimal("13");
			System.out.println("Oryginalna kwota w USD: " + usdAmount);
			System.out.println("Kwota przeliczona na PLN: " + currencyConverter.convert(usdAmount, "USD", "PLN"));
			System.out.println("Kwota przeliczona na EUR: " + currencyConverter.convert(usdAmount, "USD", "EUR"));
			System.out.println("Kwota przeliczona na wszytkie znane waluty: " + currencyConverter.convertToAll(usdAmount, "USD"));
			
			BigDecimal eurAmount = new BigDecimal("17");
			System.out.println("Oryginalna kwota w EUR: " + eurAmount);
			System.out.println("Kwota przeliczona na PLN: " + currencyConverter.convert(eurAmount, "EUR", "PLN"));
			System.out.println("Kwota przeliczona na USD: " + currencyConverter.convert(eurAmount, "EUR", "USD"));
			System.out.println("Kwota przeliczona na wszytkie znane waluty: " + currencyConverter.convertToAll(eurAmount, "EUR"));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Context createInitialContext() throws NamingException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		return new InitialContext(jndiProperties);
	}
}
