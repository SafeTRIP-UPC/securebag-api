package com.upc.securebagapi.insurance.domain.models.entity;


import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import com.upc.securebagapi.insurance.domain.models.enums.PolicyEnum;
import com.upc.securebagapi.valuation.domain.models.entity.Luggage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "policy")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id", nullable = false, updatable = false)
    private Long policyId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "luggage_id", nullable = false)
    private Luggage luggageId;

    @Column(name = "coverage_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal coverageAmount;

    @Column(name = "premium_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal premiumAmount;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 9)
    private PolicyEnum status;

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = PolicyEnum.ACTIVE;
        }
    }
}
