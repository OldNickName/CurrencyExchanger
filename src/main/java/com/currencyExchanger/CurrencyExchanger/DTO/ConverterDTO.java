package com.currencyExchanger.CurrencyExchanger.DTO;

import com.currencyExchanger.CurrencyExchanger.Validation.CustomCurrencyValidation;

import java.math.BigDecimal;

@SuppressWarnings("ALL")
public class ConverterDTO {
    @CustomCurrencyValidation(message = "wrong fromCurrency")
    private final String fromCurrency;
    @CustomCurrencyValidation(message = "wrong toCurrency")
    private final String toCurrency;
    private final BigDecimal startAmount;
    private final BigDecimal convertedAmount;

    public ConverterDTO(String fromCurrency, String toCurrency, BigDecimal startAmount, BigDecimal convertedAmount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.startAmount = startAmount;
        this.convertedAmount = convertedAmount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public BigDecimal getStartAmount() {
        return startAmount;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }
}
