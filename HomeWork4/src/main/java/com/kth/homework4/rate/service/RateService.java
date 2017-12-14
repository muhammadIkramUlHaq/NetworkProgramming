package com.kth.homework4.rate.service;

import com.kth.homework4.currency.domain.Currency;
import com.kth.homework4.currency.repository.CurrencyRepository;
import com.kth.homework4.currency.service.CurrencyService;
import com.kth.homework4.rate.domain.Rate;
import com.kth.homework4.rate.repository.RateRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

public class RateService{
    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private CurrencyService currencyService;
    
    public String listRates(ModelMap model) {
        model.addAttribute("rate", new Rate());
        model.addAttribute("rates", rateRepository.findAll());
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        return "rates";
    }

    public String saveRate(Rate rate) {
        rateRepository.save(rate);
        return "redirect:/api/rate";
    }

    public String deleteRate(Long rateId){
        rateRepository.delete(rateRepository.findOne(rateId));
        return "redirect:/api/rate";
    }

    public String listRatesAsJsonString(ModelMap map) throws JSONException {
        JSONArray rateArray = new JSONArray();
        for (Rate rate : rateRepository.findAll()) {
            JSONObject rateJSON = new JSONObject();
            rateJSON.put("id", rate.getId());
            rateJSON.put("Source Currency", rate.getConvertFrom());
            rateJSON.put("Destination Currency", rate.getConvertTo());
            rateJSON.put("Exchange Rate", rate.getExchangeRate());
            rateArray.put(rateJSON);
        }
        return rateArray.toString();
    }


}
