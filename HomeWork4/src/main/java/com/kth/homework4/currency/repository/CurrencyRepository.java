package com.kth.homework4.currency.repository;

import com.kth.homework4.currency.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    
}
