package com.upc.securebagapi.valuation.infrastructure.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class DetectionResponse {

    private Long detectionId;
    private List<String> labels;
    private Double boundingBoxX;
    private Double boundingBoxY;
    private Double boundingBoxWidth;
    private Double boundingBoxHeight;
    private Double value;
}
