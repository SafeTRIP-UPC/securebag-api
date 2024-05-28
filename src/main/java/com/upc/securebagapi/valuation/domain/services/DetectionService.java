package com.upc.securebagapi.valuation.domain.services;

import com.upc.securebagapi.valuation.domain.models.entity.Detection;
import com.upc.securebagapi.valuation.infrastructure.dto.request.DetectionRequest;

import java.util.Optional;

public interface DetectionService {
    Long createDetection(DetectionRequest detectionRequest);
    Optional<Detection> getDetectionById(Long detectionId);
    Detection updateDetectionById(Long detectionId, DetectionRequest detectionRequest);
    void deleteDetectionById(Long detectionId);
}
