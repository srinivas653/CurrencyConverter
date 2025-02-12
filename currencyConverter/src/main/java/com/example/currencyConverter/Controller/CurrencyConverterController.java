package com.example.currencyConverter.Controller;

import com.example.currencyConverter.Service.CurrencyConverterService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrencyConverterController {
    private final CurrencyConverterService service;

    public CurrencyConverterController(CurrencyConverterService service) {
        this.service = service;
    }

    @GetMapping("/rates")
    public Map<String, Object> getExchangeRates(@RequestParam(defaultValue = "USD") String base) {
        return service.getExchangeRates(base);
    }

    @PostMapping("/convert")
    public Map<String, Object> convertCurrency(@RequestBody Map<String, Object> request) {
        return service.convertCurrency(request);
    }
}