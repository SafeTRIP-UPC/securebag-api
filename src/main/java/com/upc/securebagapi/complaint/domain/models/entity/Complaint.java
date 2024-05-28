package com.upc.securebagapi.complaint.domain.models.entity;


import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import com.upc.securebagapi.complaint.domain.models.enums.ComplaintEnum;
import com.upc.securebagapi.insurance.domain.models.entity.Policy;
import com.upc.securebagapi.shared.domain.auditablemodel.AuditableModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "complaint")
public class Complaint extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id", nullable = false, updatable = false)
    private Long complaintId;

    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policyId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 8)
    private ComplaintEnum status;

    @PrePersist
    protected void onCreate() {
        if (status == null) {
            status = ComplaintEnum.PENDING;
        }
    }
}
