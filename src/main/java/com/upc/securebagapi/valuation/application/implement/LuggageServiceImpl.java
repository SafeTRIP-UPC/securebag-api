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
    public Optional<Luggage> getLuggageById(Long id) {
        return luggageRepository.findById(id);
    }

    @Override
    public Luggage updateLuggageById(Long id, LuggageRequest luggageRequest) {
        Luggage luggageToUpdate = luggageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Luggage not found with id: " + id));
        modelMapper.map(luggageRequest, luggageToUpdate);

        return luggageRepository.save(luggageToUpdate);
    }

    @Override
    public void deleteLuggageById(Long id) {
        if (!luggageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Luggage not found with id: " + id);
        }
        luggageRepository.deleteById(id);
    }
}
