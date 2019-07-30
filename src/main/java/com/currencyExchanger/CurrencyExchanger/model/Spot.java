package com.currencyExchanger.CurrencyExchanger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@SuppressWarnings("unused")
@Entity
public class Spot {

    @Id
    @Column(nullable = false, unique = true)
    private String currency;

    @Column(nullable = false, unique = true)
    private BigDecimal spot;


    public Spot(){}

    public Spot(String currency, BigDecimal spot){
        this.currency = currency;
        this.spot = spot;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getSpot() {
        return spot;
    }

    public void setSpot(BigDecimal spot) {
        this.spot = spot;
    }
}
