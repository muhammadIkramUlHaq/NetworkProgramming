package com.kth.homework4.currency.service;

import com.kth.homework4.currency.domain.Currency;
import com.kth.homework4.currency.repository.CurrencyRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public String listCurrencies(ModelMap model) {
        model.addAttribute("currency", new Currency());
        model.addAttribute("currencies", currencyRepository.findAll());
        return "currencies";
    }
    
    public String saveCurrency(Currency currency) {
        currencyRepository.save(currency);
        return "redirect:/api/currency";
    }

    public String deleteCurrency(Long currencyId){
        currencyRepository.delete(currencyRepository.findOne(currencyId));
        return "redirect:/api/currency";
    }

    public String listCurrenciesAsJsonString(ModelMap map) throws JSONException {
        JSONArray currencyArray = new JSONArray();
        for (Currency currency : currencyRepository.findAll()) {
            JSONObject currencyJSON = new JSONObject();
            currencyJSON.put("id", currency.getId());
            currencyJSON.put("Country", currency.getCountry());
            currencyJSON.put("Currency Code", currency.getCurrencyCode());
            currencyArray.put(currencyJSON);
        }
        return currencyArray.toString();
    }

    public List<String> getAllCurrencies(){
        List<String> listOfCurrencies = new ArrayList<String>();
        for(Currency currency : currencyRepository.findAll()){
            listOfCurrencies.add(currency.getCurrencyCode());
        }

        return listOfCurrencies;
    }
}
