package com.upc.securebagapi.valuation.infrastructure.controller;


import com.upc.securebagapi.shared.domain.exceptions.ResourceNotFoundException;
import com.upc.securebagapi.valuation.domain.models.entity.Detection;
import com.upc.securebagapi.valuation.domain.services.DetectionService;
import com.upc.securebagapi.valuation.infrastructure.dto.request.DetectionRequest;
import com.upc.securebagapi.valuation.infrastructure.dto.response.DetectionResponse;
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
@RequestMapping(value = "/api/v1/detection", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Detection", description = "Detection API")
public class DetectionController {

    private final DetectionService detectionService;
    private final ModelMapper modelMapper;

    @Transactional
    @PostMapping(value = "")//CALCULAR VALOR DE LA DETECCION...!!!
    public ResponseEntity<DetectionResponse> create(@Valid @RequestBody DetectionRequest detectionRequest) {
        Long detectionId = detectionService.createDetection(detectionRequest);
        Detection detectionCreated = detectionService.getDetectionById(detectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Detection not found with id: " + detectionId));
        DetectionResponse detectionResponse = modelMapper.map(detectionCreated, DetectionResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(detectionResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<DetectionResponse> get(@PathVariable Long id) {
        Detection detectionFound = detectionService.getDetectionById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detection not found with id: " + id));
        DetectionResponse detectionResponse = modelMapper.map(detectionFound, DetectionResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(detectionResponse);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<DetectionResponse> update(@PathVariable Long id, @Valid @RequestBody DetectionRequest detectionRequest) {
        Detection detectionUpdated = detectionService.updateDetectionById(id, detectionRequest);
        DetectionResponse detectionResponse = modelMapper.map(detectionUpdated, DetectionResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(detectionResponse);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        detectionService.deleteDetectionById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
