package com.kth.homework4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CurrencyExchangeController {

    @RequestMapping("/welcome")
    public String welcome() {
        return ("welcome");
    }
}
