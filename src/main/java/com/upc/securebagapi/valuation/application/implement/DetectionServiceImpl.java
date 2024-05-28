package com.upc.securebagapi.valuation.application.implement;


import com.upc.securebagapi.shared.domain.exceptions.ResourceNotFoundException;
import com.upc.securebagapi.valuation.domain.models.entity.Detection;
import com.upc.securebagapi.valuation.domain.services.DetectionService;
import com.upc.securebagapi.valuation.infrastructure.dto.request.DetectionRequest;
import com.upc.securebagapi.valuation.infrastructure.repository.DetectionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetectionServiceImpl implements DetectionService {

    private final DetectionRepository detectionRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long createDetection(DetectionRequest detectionRequest) {
        Detection detection = new Detection();
        modelMapper.map(detectionRequest, detection);
        double valueCalculated = calculateDetectionValue(
                detection.getLabels(),
                detection.getBoundingBoxWidth(),
                detection.getBoundingBoxHeight()
        );
        detection.setValue(valueCalculated);

        return detectionRepository.save(detection).getDetectionId();
    }
    private double calculateDetectionValue(List<String> labels, Double width, Double height) {
        double totalValue = 0.0;
        for (String label : labels) {
            double baseValue = getValueForLabel(label);
            double sizeFactor = calculateSizeFactor(width, height);
            totalValue += baseValue * sizeFactor;
        }
        return totalValue;
    }
    private double calculateSizeFactor(Double width, Double height) {
        // Ejemplo de lógica para ajustar el valor basado en el tamaño
        // Ajustar este cálculo según necesidad...
        return (width * height) / 10000.0; // Factor de ajuste basado en el área
    }
    private double getValueForLabel(String label) {
        String labelLowerCase = label.toLowerCase();
        return findValueForLabel(labelLowerCase);
    }
    private double findValueForLabel(String label) {
        List<String> valuableItems = List.of("jewelry", "electronics", "money", "documents");
        List<String> dangerousItems = List.of("weapons", "drugs", "explosives");
        if (valuableItems.contains(label)) {
            return 100.0;
        } else if (dangerousItems.contains(label)) {
            return 200.0;
        }
        return 50.0;
    }

    @Override
    public Optional<Detection> getDetectionById(Long detectionId) {
        return detectionRepository.findById(detectionId);
    }

    @Override
    public Detection updateDetectionById(Long detectionId, DetectionRequest detectionRequest) {
        Detection detectionToUpdate = detectionRepository.findById(detectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Detection not found with id: " + detectionId));
        modelMapper.map(detectionRequest, detectionToUpdate);

        return detectionRepository.save(detectionToUpdate);
    }

    @Override
    public void deleteDetectionById(Long detectionId) {
        if (!detectionRepository.existsById(detectionId)) {
            throw new ResourceNotFoundException("Detection not found with id: " + detectionId);
        }
        detectionRepository.deleteById(detectionId);
    }
}
