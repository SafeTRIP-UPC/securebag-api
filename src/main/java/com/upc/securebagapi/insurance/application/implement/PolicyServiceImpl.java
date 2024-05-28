package com.upc.securebagapi.insurance.application.implement;


import com.upc.securebagapi.auth.infrastructure.repository.UserRepository;
import com.upc.securebagapi.insurance.domain.models.entity.Policy;
import com.upc.securebagapi.insurance.domain.services.PolicyService;
import com.upc.securebagapi.insurance.infrastructure.dto.request.PolicyRequest;
import com.upc.securebagapi.insurance.infrastructure.repository.PolicyRepository;
import com.upc.securebagapi.shared.domain.exceptions.ResourceNotFoundException;
import com.upc.securebagapi.valuation.domain.models.entity.Luggage;
import com.upc.securebagapi.valuation.infrastructure.dto.request.LuggageRequest;
import com.upc.securebagapi.valuation.infrastructure.repository.LuggageRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;
    private final LuggageRepository luggageRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long createPolicy(PolicyRequest policyRequest) {
        if (userRepository.existsByUserId(policyRequest.getUserEntity().getUserId())) {
            throw new ValidationException("Policy already has a User created with id: " + policyRequest.getUserEntity().getUserId());
        }
        if (luggageRepository.existsByLuggageId(policyRequest.getLuggageEntity().getLuggageId())) {
            throw new ValidationException("Policy already has a luggage created with id: " + policyRequest.getLuggageEntity().getLuggageId());
        }
        Policy policy = new Policy();
        modelMapper.map(policyRequest, policy);

        return policyRepository.save(policy).getPolicyId();
    }

    @Override
    public Optional<Policy> getPolicyById(Long id) {
        return policyRepository.findById(id);
    }

    @Override
    public Policy updatePolicyById(Long id, PolicyRequest luggageRequest) {
        Policy policyToUpdate = policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with id: " + id));
        modelMapper.map(luggageRequest, policyToUpdate);

        return policyRepository.save(policyToUpdate);
    }

    @Override
    public void deletePolicyById(Long id) {
        if (!policyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Policy not found with id: " + id);
        }
        policyRepository.deleteById(id);
    }
}
