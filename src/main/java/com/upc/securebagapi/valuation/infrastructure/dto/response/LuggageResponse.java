package com.upc.securebagapi.valuation.infrastructure.dto.response;


import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class LuggageResponse {

    private Long luggageId;
    private UserEntity userId;
    private String description;
    private BigDecimal estimatedValue;
    private Timestamp scannedAt;
}
