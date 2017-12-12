package com.kth.homework4.currency.service;

import com.kth.homework4.currency.domain.Currency;
import com.kth.homework4.currency.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

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
        return "redirect:/";
    }

    public String deleteCurrency(Long currencyId){
        currencyRepository.delete(currencyRepository.findOne(currencyId));
        return "redirect:/";
    }



}
