package com.upc.securebagapi.valuation.domain.models.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "detection")
public class Detection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detection_id", nullable = false, updatable = false)
    private Long detectionId;

    @ElementCollection
    @CollectionTable(
            name = "label",
            joinColumns = @JoinColumn(name = "detection_id")
    )
    @Column(name = "labels", nullable = false)
    private List<String> labels;

    @Column(name = "bounding_box_x", nullable = false)
    private Double boundingBoxX;

    @Column(name = "bounding_box_y", nullable = false)
    private Double boundingBoxY;

    @Column(name = "bounding_box_width", nullable = false)
    private Double boundingBoxWidth;

    @Column(name = "bounding_box_height", nullable = false)
    private Double boundingBoxHeight;

    @Column(name = "value", nullable = false)
    private Double value;
}
