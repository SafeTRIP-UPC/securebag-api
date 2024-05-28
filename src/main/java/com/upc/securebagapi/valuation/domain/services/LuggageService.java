package com.upc.securebagapi.valuation.domain.services;

import com.upc.securebagapi.valuation.domain.models.entity.Luggage;
import com.upc.securebagapi.valuation.infrastructure.dto.request.LuggageRequest;

import java.util.Optional;

public interface LuggageService {
    Long createLuggage(LuggageRequest luggageRequest);
    Optional<Luggage> getLuggageById(Long luggageId);
    Luggage updateLuggageById(Long luggageId, LuggageRequest luggageRequest);
    void deleteLuggageById(Long luggageId);
}
