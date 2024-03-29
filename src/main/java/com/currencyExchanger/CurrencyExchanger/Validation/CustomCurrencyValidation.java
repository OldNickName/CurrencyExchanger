package com.currencyExchanger.CurrencyExchanger.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@SuppressWarnings("unused")
@Documented
@Constraint(validatedBy = CurrencyValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomCurrencyValidation {
    String message() default "Invalid currency";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}