package com.szalak.nest.nbp;

import com.szalak.nest.exceptions.IllegalCountryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;
    private RestTemplate restTemplate;
    private RatesRepository ratesRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, RestTemplate restTemplate, RatesRepository ratesRepository) {
        this.currencyRepository = currencyRepository;
        this.restTemplate = restTemplate;
        this.ratesRepository = ratesRepository;
    }


    public List<Rates> calculateBestPrices(GetCurrency getCurrency) {
        List<List<Rates>> currencyList = currencyRepository.findAll()
                .stream()
                .filter(x -> x.getCurrency().equals(getCurrency.getCurrecny()))
                .map(Currency::getRates)
                .collect(Collectors.toList());

        List<Rates> ratesOfSpecificCurrency = new ArrayList<>();
        currencyList.forEach(ratesOfSpecificCurrency::addAll);

        List<Rates> maxBidAndMinAskRateList = new ArrayList<>();
        findMaxBidPrice(ratesOfSpecificCurrency).ifPresent(maxBidAndMinAskRateList::add);
        findMinAskPrice(ratesOfSpecificCurrency).ifPresent(maxBidAndMinAskRateList::add);
        return maxBidAndMinAskRateList;
    }

    public List<Currency> findCurrencyRatesBetweenDatesInDataBase(GetCurrency getCurrency) throws IllegalCountryException {
        String currency = getCurrency.getCurrecny();
        LocalDate startDate = LocalDate.parse(getCurrency.getStartDate());
        LocalDate endDate = LocalDate.parse(getCurrency.getEndDate());

        //to simplify I've focused on currencies listed in enum Currencies so I check if provided currency is valid
        //then search DB for rates within date range
        if (Arrays.stream(Currencies.values()).anyMatch((x -> x.name().equals(currency)))) {
            return currencyRepository.findByCurrency(currency)
                    .stream()
                    .filter(x -> x.getRates()
                            .stream()
                            .anyMatch(rate -> LocalDate.parse(rate.getEffectiveDate()).isAfter(startDate.minusDays(1)) &&
                                    LocalDate.parse(rate.getEffectiveDate()).isBefore(endDate.plusDays(1))))
                    .collect(Collectors.toList());
        } else throw new IllegalCountryException("type one of following: us_dollar, russian_ruble, euro");
    }

    //method to get connected with NBP's api with the usage of RestTemplate
    public Currency getCurrenciesRatesBetweenDatesFromNbpApi(GetCurrency getCurrency) {
        return restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/c/" +
                        mapCurrencyWithCode(getCurrency.getCurrecny())
                        + "/" + getCurrency.getStartDate()
                        + "/" + getCurrency.getEndDate()
                        + "/?format=json",
                Currency.class);
    }

    private String mapCurrencyWithCode(String currency) {
        Map<String, String> currenciesMappedWithCodes = new HashMap<>();

        currenciesMappedWithCodes.put(Currencies.us_dollar.toString(), "usd");
        currenciesMappedWithCodes.put(Currencies.russian_ruble.toString(), "rub");
        currenciesMappedWithCodes.put(Currencies.euro.toString(), "eur");

        return currenciesMappedWithCodes.get(currency);
    }
    private Optional<Rates> findMaxBidPrice(List<Rates> ratesOfSpecificCurrency) {
        return ratesOfSpecificCurrency.stream()
                .filter(x -> x.getBid() != 0)
                .max(Comparator.comparingDouble(Rates::getBid));
    }
    private Optional<Rates> findMinAskPrice(List<Rates> ratesOfSpecificCurrency) {
        return ratesOfSpecificCurrency.stream()
                .filter(x -> x.getAsk() != 0)
                .min(Comparator.comparingDouble(Rates::getAsk));
    }
    public void saveCurrencyRate(Currency currency) {
        currencyRepository.save(currency);
    }
}






