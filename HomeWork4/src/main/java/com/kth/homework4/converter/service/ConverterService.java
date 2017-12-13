package com.kth.homework4.converter.service;

import com.kth.homework4.currency.repository.CurrencyRepository;
import com.kth.homework4.rate.domain.Rate;
import com.kth.homework4.rate.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ConverterService {

    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

    public String exchangeCurrency(ModelMap model) {
        model.addAttribute("currencies", currencyRepository.findAll());
        model.addAttribute("rates", rateRepository.findAll());
        return "converter";
    }

    public String convert(HttpServletRequest request){
        String returnText;
        String amountToBeConverted = request.getParameter("amountToBeConverted");
        String convertFrom =  request.getParameter("convertFrom");
        String convertTo =  request.getParameter("convertTo");
        BigDecimal amountToBeConvertedInBigDecimal = new BigDecimal(amountToBeConverted.replaceAll(",", ""));
        BigDecimal convertedAmount = convertAmount(amountToBeConvertedInBigDecimal,convertFrom,convertTo) ;
        returnText = " " +  amountToBeConverted + "  " + convertFrom +  " = " + convertedAmount + "  " +  convertTo;
        return returnText;
    }

    public BigDecimal convertAmount(BigDecimal amountToConvert, String convertFrom , String convertTo) {
        BigDecimal exchangeRate = new BigDecimal(0);
        for (Rate rate:rateRepository.findAll()){
            if (rate.getConvertFrom().equalsIgnoreCase(convertFrom) && rate.getConvertTo().equalsIgnoreCase(convertTo)){
                   exchangeRate = rate.getExchangeRate();
            }
        }
        BigDecimal convertedAmount = exchangeRate.multiply(amountToConvert) ;
        return convertedAmount;
    }


}
