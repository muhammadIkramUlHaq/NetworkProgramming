package com.kth.homework4.currency.rest;

import com.kth.homework4.currency.service.CurrencyService;
import com.kth.homework4.currency.domain.Currency;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/api/currency", method = RequestMethod.GET)
    public String listCurrencies(ModelMap model) {
       return currencyService.listCurrencies(model);

    }

    @RequestMapping(value = "/api/currency/add", method = RequestMethod.POST)
    public String addCurrency(@ModelAttribute("currency") Currency currency, BindingResult result) {
        return currencyService.saveCurrency(currency);

    }

    @RequestMapping("/api/currency/delete/{Id}")
    public String deleteCurrency(@PathVariable("Id") Long currencyId) {
       return currencyService.deleteCurrency(currencyId);
    }

    @RequestMapping(value = "/api/currencies", method = RequestMethod.GET)
    @ResponseBody
    public String listCurrenciesJson(ModelMap model) throws JSONException {
        return currencyService.listCurrenciesAsJsonString(model);
    }
}