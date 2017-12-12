package com.kth.homework4.rate.repository;

import com.kth.homework4.rate.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RateRepository extends JpaRepository<Rate, Long> {

}
