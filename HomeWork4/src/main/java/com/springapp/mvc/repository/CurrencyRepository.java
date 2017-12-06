package com.springapp.mvc.repository;

import com.springapp.mvc.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    
}
