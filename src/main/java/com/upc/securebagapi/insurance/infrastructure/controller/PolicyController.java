package com.upc.securebagapi.insurance.infrastructure.controller;


import com.upc.securebagapi.insurance.domain.models.entity.Policy;
import com.upc.securebagapi.insurance.domain.services.PolicyService;
import com.upc.securebagapi.insurance.infrastructure.dto.request.PolicyRequest;
import com.upc.securebagapi.insurance.infrastructure.dto.response.PolicyResponse;
import com.upc.securebagapi.shared.domain.exceptions.ResourceNotFoundException;
import com.upc.securebagapi.valuation.domain.models.entity.Luggage;
import com.upc.securebagapi.valuation.domain.services.LuggageService;
import com.upc.securebagapi.valuation.infrastructure.dto.request.LuggageRequest;
import com.upc.securebagapi.valuation.infrastructure.dto.response.LuggageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/policy", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Policy", description = "Policy API")
public class PolicyController {

    private final PolicyService policyService;
    private final ModelMapper modelMapper;

    @Transactional
    @PostMapping(value = "")
    public ResponseEntity<PolicyResponse> create(@Valid @RequestBody PolicyRequest policyRequest) {
        Long policyId = policyService.createPolicy(policyRequest);
        Policy policyCreated = policyService.getPolicyById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with id: " + policyId));
        PolicyResponse policyResponse = modelMapper.map(policyCreated, PolicyResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(policyResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<PolicyResponse> get(@PathVariable Long id) {
        Policy policyFound = policyService.getPolicyById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with id: " + id));
        PolicyResponse policyResponse = modelMapper.map(policyFound, PolicyResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(policyResponse);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<PolicyResponse> update(@PathVariable Long id, @Valid @RequestBody PolicyRequest policyRequest) {
        Policy policyUpdated = policyService.updatePolicyById(id, policyRequest);
        PolicyResponse policyResponse = modelMapper.map(policyUpdated, PolicyResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(policyResponse);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        policyService.deletePolicyById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
