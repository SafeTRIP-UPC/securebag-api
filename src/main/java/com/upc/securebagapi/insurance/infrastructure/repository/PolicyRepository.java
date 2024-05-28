package com.upc.securebagapi.insurance.infrastructure.repository;


import com.upc.securebagapi.insurance.domain.models.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    boolean existsByPolicyId(Long policyId);
}
