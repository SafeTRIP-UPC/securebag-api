package com.upc.securebagapi.valuation.infrastructure.controller;


import com.upc.securebagapi.shared.domain.exceptions.ResourceNotFoundException;
import com.upc.securebagapi.valuation.domain.models.entity.Luggage;
import com.upc.securebagapi.valuation.domain.services.LuggageService;
import com.upc.securebagapi.valuation.infrastructure.dto.request.LuggageRequest;
import com.upc.securebagapi.valuation.infrastructure.dto.response.LuggageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/luggage", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Luggage", description = "Luggage API")
public class LuggageController {

    private final LuggageService luggageService;
    private final ModelMapper modelMapper;

    @Transactional
    @PostMapping(value = "")
    public ResponseEntity<LuggageResponse> create(@Valid @RequestBody LuggageRequest luggageRequest) {
        Long luggageId = luggageService.createLuggage(luggageRequest);
        Luggage luggageCreated = luggageService.getLuggageById(luggageId)
                .orElseThrow(() -> new ResourceNotFoundException("Luggage not found with id: " + luggageId));
        LuggageResponse luggageResponse = modelMapper.map(luggageCreated, LuggageResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(luggageResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<LuggageResponse> get(@PathVariable Long id) {
        Luggage luggageFound = luggageService.getLuggageById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Luggage not found with id: " + id));
        LuggageResponse luggageResponse = modelMapper.map(luggageFound, LuggageResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(luggageResponse);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<LuggageResponse> update(@PathVariable Long id, @Valid @RequestBody LuggageRequest luggageRequest) {
        Luggage luggageUpdated = luggageService.updateLuggageById(id, luggageRequest);
        LuggageResponse luggageResponse = modelMapper.map(luggageUpdated, LuggageResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(luggageResponse);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        luggageService.deleteLuggageById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
