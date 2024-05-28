package com.upc.securebagapi.complaint.infrastructure.dto.response;

import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import com.upc.securebagapi.complaint.domain.models.enums.ComplaintEnum;
import com.upc.securebagapi.insurance.domain.models.entity.Policy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@NoArgsConstructor
@Getter
@Setter
public class ComplaintResponse {

    private Long complaintId;
    private Policy policy;
    private UserEntity userId;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private ComplaintEnum status;
}
