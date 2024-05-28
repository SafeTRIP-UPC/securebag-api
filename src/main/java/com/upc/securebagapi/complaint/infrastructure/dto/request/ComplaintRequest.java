package com.upc.securebagapi.complaint.infrastructure.dto.request;

import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import com.upc.securebagapi.insurance.domain.models.entity.Policy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class ComplaintRequest {

    @NotBlank(message = "Policy id is required")
    @Positive(message = "Policy id must be positive")
    private Policy policyEntity;

    @NotBlank(message = "User id is required")
    @Positive(message = "User id must be positive")
    private UserEntity userEntity;

    @NotBlank(message = "Description is required")
    private String description;
}
