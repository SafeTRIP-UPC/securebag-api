package com.upc.securebagapi.valuation.infrastructure.dto.request;


import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class LuggageRequest {

    @NotBlank(message = "User id is required")
    @Positive(message = "User id must be positive")
    private UserEntity userEntity;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Estimated value is required")
    @Positive(message = "Estimated value must be positive")
    private BigDecimal estimatedValue;
}
