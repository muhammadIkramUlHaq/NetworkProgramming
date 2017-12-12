package com.kth.homework4.currency.rest;

import com.kth.homework4.currency.service.CurrencyService;
import com.kth.homework4.currency.domain.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listCurrencies(ModelMap model) {
       return currencyService.listCurrencies(model);

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCurrency(@ModelAttribute("currency") Currency currency, BindingResult result) {
        return currencyService.saveCurrency(currency);

    }

    @RequestMapping("/delete/{Id}")
    public String deleteCurrency(@PathVariable("Id") Long currencyId) {
       return currencyService.deleteCurrency(currencyId);
    }
}