package com.upc.securebagapi.valuation.infrastructure.repository;

import com.upc.securebagapi.valuation.domain.models.entity.Detection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DetectionRepository extends JpaRepository<Detection, Long> {
}
