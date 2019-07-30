package com.currencyExchanger.CurrencyExchanger.repository;

import com.currencyExchanger.CurrencyExchanger.model.Spot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotsRepository extends CrudRepository<Spot, String > {
    Spot findByCurrency(String currency);
}
