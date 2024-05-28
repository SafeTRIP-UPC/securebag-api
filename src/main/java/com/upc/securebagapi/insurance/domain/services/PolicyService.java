package com.upc.securebagapi.insurance.domain.services;

import com.upc.securebagapi.insurance.domain.models.entity.Policy;
import com.upc.securebagapi.insurance.infrastructure.dto.request.PolicyRequest;
import com.upc.securebagapi.valuation.domain.models.entity.Luggage;
import com.upc.securebagapi.valuation.infrastructure.dto.request.LuggageRequest;

import java.util.Optional;


public interface PolicyService {
    Long createPolicy(PolicyRequest luggageRequest);
    Optional<Policy> getPolicyById(Long policyId);
    Policy updatePolicyById(Long policyId, PolicyRequest luggageRequest);
    void deletePolicyById(Long policyId);
}
