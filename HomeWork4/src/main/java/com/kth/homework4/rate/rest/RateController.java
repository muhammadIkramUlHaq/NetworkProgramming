package com.kth.homework4.rate.rest;

import com.kth.homework4.rate.domain.Rate;
import com.kth.homework4.rate.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class RateController {
    @Autowired
    private RateService rateService;

    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    public String listRates(ModelMap model) {
        return rateService.listRates(model);

    }

    @RequestMapping(value = "/rate/add", method = RequestMethod.POST)
    public String addRate(@ModelAttribute("rate") Rate rate, BindingResult result) {
        return rateService.saveRate(rate);

    }

    @RequestMapping("/rate/delete/{Id}")
    public String deleteRate(@PathVariable("Id") Long rateId) {
        return rateService.deleteRate(rateId);
    }
}