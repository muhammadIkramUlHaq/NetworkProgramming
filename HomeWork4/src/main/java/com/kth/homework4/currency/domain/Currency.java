package com.kth.homework4.currency.domain;

import javax.persistence.*;

@Entity (name = "Currency")
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

    public String getCountry(){
        return country;
    }

    public void setCountry (String country) {
        this.country = country;
    }

    public String getCurrencyCode(){
        return currencyCode;
    }

    public void setCurrencyCode (String currencyCode) {
        this.currencyCode = currencyCode;
    }
    
}