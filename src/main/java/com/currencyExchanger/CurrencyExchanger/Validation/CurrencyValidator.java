package com.currencyExchanger.CurrencyExchanger.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;

public class CurrencyValidator implements
        ConstraintValidator<CustomCurrencyValidation, String> {

    @SuppressWarnings("EmptyMethod")
    @Override
    public void initialize(CustomCurrencyValidation currency) {
    }

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext constraintValidatorContext) {
        boolean currencyStatus;
        try {
            currencyStatus = Currency.getAvailableCurrencies().contains(Currency.getInstance(currency));
        } catch (RuntimeException e) {
            currencyStatus = false;
        }
        return currency != null && currencyStatus;
    }
}
