package com.upc.securebagapi.valuation.domain.models.entity;


import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "luggage")
public class Luggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "luggage_id", nullable = false, updatable = false)
    private Long luggageId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "estimated_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal estimatedValue;

    @Column(name = "scanned_at", nullable = false, updatable = false)
    private Timestamp scannedAt;

    @PrePersist
    protected void onCreate() {
        if (scannedAt == null) {
            scannedAt = new Timestamp(System.currentTimeMillis());
        }
    }
}
