package com.upc.securebagapi.valuation.infrastructure.repository;


import com.upc.securebagapi.valuation.domain.models.entity.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LuggageRepository extends JpaRepository<Luggage, Long> {
}
