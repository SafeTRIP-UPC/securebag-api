package com.upc.securebagapi.valuation.infrastructure.dto.request;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class DetectionRequest {

    @NotEmpty(message = "labels is required")
    private List<String> labels;

    @NotNull(message = "boundingBoxX is required")
    @Positive(message = "boundingBoxX must be positive")
    private Double boundingBoxX;

    @NotNull(message = "boundingBoxY is required")
    @Positive(message = "boundingBoxY must be positive")
    private Double boundingBoxY;

    @NotNull(message = "boundingBoxWidth is required")
    @Positive(message = "boundingBoxWidth must be positive")
    private Double boundingBoxWidth;

    @NotNull(message = "boundingBoxHeight is required")
    @Positive(message = "boundingBoxHeight must be positive")
    private Double boundingBoxHeight;
}
