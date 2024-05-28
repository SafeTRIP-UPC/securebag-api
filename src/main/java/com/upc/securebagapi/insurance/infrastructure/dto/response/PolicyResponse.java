package com.upc.securebagapi.insurance.infrastructure.dto.response;


import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import com.upc.securebagapi.insurance.domain.models.enums.PolicyEnum;
import com.upc.securebagapi.valuation.domain.models.entity.Luggage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PolicyResponse {

    private Long policyId;
    private UserEntity userId;
    private Luggage luggageId;
    private BigDecimal coverageAmount;
    private BigDecimal premiumAmount;
    private Date startDate;
    private Date endDate;
    private PolicyEnum status;
}
