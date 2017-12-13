package com.kth.homework4.rate.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity (name="Rate")
public class Rate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Basic
    private String convertFrom;

    @Basic
    private String convertTo;

    @Basic
    private BigDecimal exchangeRate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setExchangeRate (BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}
