package com.kth.homework4.currency.domain;

import javax.persistence.*;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String country;

    @Basic
    private String currencyCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName(){
        return country;
    }

    public void setCountryName (String countryName) {
        this.country = countryName;
    }

    public String getCurrencyCode(){
        return currencyCode;
    }

    public void setCountryCode (String currencyCode) {
        this.currencyCode = currencyCode;
    }
    
}