package com.szalak.nest.nbp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class CurrencyEndpoint {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyEndpoint(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PayloadRoot(namespace = "http://szalak.com/zadanie", localPart = "getCurrency")
    @ResponsePayload
    public GetResponse findCurrencyRatesBetweenDates(@RequestPayload GetCurrency getCurrency) {
        GetResponse getResponse = new GetResponse();

        //search for already stored rates
        List<Currency> savedCurrencies = currencyService.findCurrencyRatesBetweenDatesInDataBase(getCurrency);

        //if not found get rates from NBP api and save to the DB.
        //if found step over to calculating prices
        if (savedCurrencies.isEmpty()) {

            List<Currency> retrievedCurrencies = new ArrayList<>();
            retrievedCurrencies.add(currencyService.getCurrenciesRatesBetweenDatesFromNbpApi(getCurrency));
            retrievedCurrencies.forEach(x -> currencyService.saveCurrencyRate(x));
        }
            //calculate and return objects having highest and lowest prices
            for (Rates calculateBestPrice : currencyService.calculateBestPrices(getCurrency)) {
                getResponse.getRates().add(calculateBestPrice);
            }return getResponse;
        }

}












