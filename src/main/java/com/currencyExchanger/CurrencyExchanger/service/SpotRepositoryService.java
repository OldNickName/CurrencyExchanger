package com.currencyExchanger.CurrencyExchanger.service;

import com.currencyExchanger.CurrencyExchanger.model.Spot;
import com.currencyExchanger.CurrencyExchanger.repository.SpotsRepository;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipInputStream;

@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "RedundantOperationOnEmptyContainer"})
@Service
public class SpotRepositoryService {

    @Autowired
    SpotsRepository spotsRepository;

    public void loadSpots() throws IOException {
        URL url = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref.zip");
        url.openConnection();
        InputStream inputStream = url.openStream();
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        zipInputStream.getNextEntry();
        LineIterator lineIterator = IOUtils.lineIterator(zipInputStream, "utf-8");
        List<String> currencyList = Arrays.asList(lineIterator.nextLine().split(","));
        List<String> spotList = Arrays.asList(lineIterator.nextLine().split(","));
        List<Spot> spots = new ArrayList<>();
        if (currencyList.size() == spotList.size()) {
            for (int i = 1; i < currencyList.size() - 1; i++) {
                spots.add(new Spot(currencyList.get(i), new BigDecimal(spotList.get(i).trim())));
            }
        } else {
            spots.clear();
        }
        lineIterator.close();
        spotsRepository.saveAll(spots);
    }

    public BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount){
        try{
            BigDecimal fromCurrencySpot = spotsRepository.findByCurrency(fromCurrency).getSpot();
            BigDecimal toCurrencySpot = spotsRepository.findByCurrency(toCurrency).getSpot();
            return (amount.multiply(toCurrencySpot)).divide(fromCurrencySpot, RoundingMode.HALF_UP);
        }catch (ConstraintViolationException e){
            return null;
        }
    }
}
