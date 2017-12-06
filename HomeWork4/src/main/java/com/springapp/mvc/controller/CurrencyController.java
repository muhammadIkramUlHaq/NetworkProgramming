package com.springapp.mvc.controller;

import com.springapp.mvc.domain.Currency;
import com.springapp.mvc.repository.CurrencyRepository;
import com.springapp.mvc.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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

    @RequestMapping("/delete/{currencyId}")
    public String deleteCurrency(@PathVariable("currencyId") Long currencyId) {
       return currencyService.deleteCurrency(currencyId);
    }
}