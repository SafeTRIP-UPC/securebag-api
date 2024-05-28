package com.upc.securebagapi.insurance.infrastructure.dto.request;


import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import com.upc.securebagapi.insurance.domain.models.enums.PolicyEnum;
import com.upc.securebagapi.valuation.domain.models.entity.Luggage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Getter
public class PolicyRequest {

    @NotBlank(message = "User id is required")
    @Positive(message = "User id must be positive")
    private UserEntity userEntity;

    @NotBlank(message = "Luggage id is required")
    @Positive(message = "Luggage id must be positive")
    private Luggage luggageEntity;

    @NotBlank(message = "Coverage amount is required")
    @Positive(message = "Coverage amount must be positive")
    private BigDecimal coverageAmount;

    @NotBlank(message = "Premium amount is required")
    @Positive(message = "Premium amount must be positive")
    private BigDecimal premiumAmount;

    @NotBlank(message = "Start date is required")
    private Date startDate;

    @NotBlank(message = "End date is required")
    private Date endDate;
}
