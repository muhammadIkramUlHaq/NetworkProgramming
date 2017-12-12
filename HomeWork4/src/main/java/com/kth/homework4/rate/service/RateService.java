package com.kth.homework4.rate.service;

import com.kth.homework4.rate.domain.Rate;
import com.kth.homework4.rate.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

public class RateService{
    @Autowired
    private RateRepository rateRepository;

    public String listRates(ModelMap model) {
        model.addAttribute("rate", new Rate());
        model.addAttribute("rates", rateRepository.findAll());
        return "rates";
    }

    public String saveRate(Rate rate) {
        rateRepository.save(rate);
        return "redirect:/rate";
    }

    public String deleteRate(Long rateId){
        rateRepository.delete(rateRepository.findOne(rateId));
        return "redirect:/rate";
    }

}
