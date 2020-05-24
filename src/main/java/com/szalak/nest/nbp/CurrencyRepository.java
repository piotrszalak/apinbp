package com.szalak.nest.nbp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Long> {
    List<Currency> findByCurrency(String currency);
}