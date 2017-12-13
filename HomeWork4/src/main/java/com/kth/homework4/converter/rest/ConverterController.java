package com.kth.homework4.converter.rest;

import com.kth.homework4.converter.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

@Controller
public class ConverterController {
    @Autowired
    ConverterService converterService;

    @RequestMapping(value = "/api/currencyExchange", method = RequestMethod.GET)
    public String exchangeCurrency(ModelMap model) {
        return converterService.exchangeCurrency(model);
    }

    @RequestMapping(value = "/api/convert", method = RequestMethod.POST)
    public
    @ResponseBody String convert(HttpServletRequest request) {
        return converterService.convert(request);
    }

}
