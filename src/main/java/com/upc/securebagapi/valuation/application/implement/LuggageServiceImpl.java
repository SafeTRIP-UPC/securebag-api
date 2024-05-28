package com.upc.securebagapi.valuation.application.implement;


import com.upc.securebagapi.auth.infrastructure.repository.UserRepository;
import com.upc.securebagapi.shared.domain.exceptions.ResourceNotFoundException;
import com.upc.securebagapi.valuation.domain.models.entity.Luggage;
import com.upc.securebagapi.valuation.domain.services.LuggageService;
import com.upc.securebagapi.valuation.infrastructure.dto.request.LuggageRequest;
import com.upc.securebagapi.valuation.infrastructure.repository.LuggageRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LuggageServiceImpl implements LuggageService {

    private final LuggageRepository luggageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long createLuggage(LuggageRequest luggageRequest) {
        if (userRepository.existsByUserId(luggageRequest.getUserEntity().getUserId())) {
            throw new ValidationException("Luggage already has a User created with id: " + luggageRequest.getUserEntity().getUserId());
        }
        Luggage luggage = new Luggage();
        modelMapper.map(luggageRequest, luggage);

        return luggageRepository.save(luggage).getLuggageId();
    }

    @Override
    public Optional<Luggage> getLuggageById(Long luggageId) {
        return luggageRepository.findById(luggageId);
    }

    @Override
    public Luggage updateLuggageById(Long luggageId, LuggageRequest luggageRequest) {
        Luggage luggageToUpdate = luggageRepository.findById(luggageId)
                .orElseThrow(() -> new ResourceNotFoundException("Luggage not found with id: " + luggageId));
        modelMapper.map(luggageRequest, luggageToUpdate);

        return luggageRepository.save(luggageToUpdate);
    }

    @Override
    public void deleteLuggageById(Long luggageId) {
        if (!luggageRepository.existsById(luggageId)) {
            throw new ResourceNotFoundException("Luggage not found with id: " + luggageId);
        }
        luggageRepository.deleteById(luggageId);
    }
}
