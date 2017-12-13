package com.kth.homework4.rate.rest;

import com.kth.homework4.rate.domain.Rate;
import com.kth.homework4.rate.service.RateService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class RateController {
    @Autowired
    private RateService rateService;

    @RequestMapping(value = "/api/rate", method = RequestMethod.GET)
    public String listRates(ModelMap model) {
        return rateService.listRates(model);

    }

    @RequestMapping(value = "/api/rate/add", method = RequestMethod.POST)
    public String addRate(@ModelAttribute("rate") Rate rate, BindingResult result) {
        return rateService.saveRate(rate);

    }

    @RequestMapping("/api/rate/delete/{Id}")
    public String deleteRate(@PathVariable("Id") Long rateId) {
        return rateService.deleteRate(rateId);
    }

    @RequestMapping(value = "/api/rates", method = RequestMethod.GET)
    @ResponseBody
    public String listRatesJson(ModelMap model) throws JSONException {
        return rateService.listRatesAsJsonString(model);
    }

}