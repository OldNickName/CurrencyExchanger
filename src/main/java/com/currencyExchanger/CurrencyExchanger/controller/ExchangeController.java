package com.currencyExchanger.CurrencyExchanger.controller;

import com.currencyExchanger.CurrencyExchanger.DTO.ErrorDTO;
import com.currencyExchanger.CurrencyExchanger.DTO.ConverterDTO;
import com.currencyExchanger.CurrencyExchanger.Validation.CustomCurrencyValidation;
import com.currencyExchanger.CurrencyExchanger.model.Spot;
import com.currencyExchanger.CurrencyExchanger.repository.SpotsRepository;
import com.currencyExchanger.CurrencyExchanger.service.SpotRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "TryWithIdenticalCatches"})
@RestController
public class ExchangeController {

    @Autowired
    private SpotsRepository spotsRepository;

    @Autowired
    private SpotRepositoryService spotRepositoryService;

    @GetMapping("")
    public Iterable findAll() {
        return spotsRepository.findAll();
    }

    @GetMapping("/load")
    public Iterable<Spot> load() throws IOException {
        spotRepositoryService.loadSpots();
        return spotsRepository.findAll();
    }

    @GetMapping("/work")
    public Object find(@RequestParam String fromCurrency,
                             @RequestParam String toCurrency,
                             @RequestParam String amount) throws IOException {
        spotRepositoryService.loadSpots();
        try{
            return new ConverterDTO(fromCurrency, toCurrency, new BigDecimal(amount),
                    spotRepositoryService.convert(fromCurrency, toCurrency, new BigDecimal(amount)));
        } catch (NullPointerException e){
            return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", "NullPointerException");
        } catch (NumberFormatException e){
            return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", "NumberFormatException");
        }
    }
}
