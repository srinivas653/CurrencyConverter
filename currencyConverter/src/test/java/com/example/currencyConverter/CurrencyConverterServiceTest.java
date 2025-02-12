package com.example.currencyConverter;



import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.currencyConverter.Service.CurrencyConverterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class CurrencyConverterServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyConverterService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetExchangeRates() {
        Map<String, Object> mockResponse = Map.of("rates", Map.of("EUR", 0.96));
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        Map<String, Object> rates = service.getExchangeRates("USD");
        assertNotNull(rates);
        assertTrue(rates.containsKey("rates"));
    }

    @Test
    void testConvertCurrency() {
        Map<String, Object> request = Map.of("from", "USD", "to", "EUR", "amount", 100);
        Map<String, Object> mockRates = Map.of("rates", Map.of("EUR", 0.96));
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockRates);

        Map<String, Object> response = service.convertCurrency(request);
        assertEquals("USD", response.get("from"));
        assertEquals("EUR", response.get("to"));
        assertEquals(100.0, ((Number) response.get("amount")).doubleValue());
        assertEquals(96.7, ((Number) response.get("convertedAmount")).doubleValue());
    }
}