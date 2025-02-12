package com.example.currencyConverter.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyConverterService {
    private final String API_URL = "https://api.exchangerate-api.com/v4/latest/";
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getExchangeRates(String base) {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(API_URL + base, Map.class);
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("External API unavailable. Please try again later.");
        }
    }

    public Map<String, Object> convertCurrency(Map<String, Object> request) {
        String from = (String) request.get("from");
        String to = (String) request.get("to");
        Number amountNumber = (Number) request.get("amount");
        double amount = amountNumber.doubleValue();

        Map<String, Object> ratesResponse = getExchangeRates(from);
        if (ratesResponse == null || !ratesResponse.containsKey("rates")) {
            throw new RuntimeException("Failed to fetch exchange rates for base currency: " + from);
        }

        Map<String, Double> rates = (Map<String, Double>) ratesResponse.get("rates");
        if (!rates.containsKey(to)) {
            throw new RuntimeException("Invalid target currency: " + to);
        }

        double rate = rates.get(to);
        double convertedAmount = Math.round(amount * rate * 100.0) / 100.0;  // Round to 2 decimal places

        return Map.of("from", from, "to", to, "amount", amount, "convertedAmount", convertedAmount);
    }
}

