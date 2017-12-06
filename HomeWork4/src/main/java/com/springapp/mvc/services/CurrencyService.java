package com.springapp.mvc.services;

import com.springapp.mvc.domain.Currency;
import com.springapp.mvc.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import javax.persistence.Entity;

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
