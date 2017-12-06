package com.springapp.mvc.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ExchangeRate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exchangeRateId;

    @Basic
    private String convertFrom;

    @Basic
    private String convertTo;

    @Basic
    private BigDecimal exchangeRate;
    
    public Long getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(Long exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }

    public String getConvertFrom(){
        return convertFrom;
    }

    public void setConvertFrom (String currencyCode) {
        this.convertFrom = currencyCode;
    }

    public String getConvertTo(){
        return convertTo;
    }

    public void setConvertTo (String currencyCode) {
        this.convertTo = currencyCode;
    }

    public BigDecimal getExchangeRate(){
        return exchangeRate;
    }

    public void setCountryCode (BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}
