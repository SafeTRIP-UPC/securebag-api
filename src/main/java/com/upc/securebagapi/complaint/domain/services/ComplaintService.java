package com.upc.securebagapi.complaint.domain.services;

import com.upc.securebagapi.complaint.domain.models.entity.Complaint;
import com.upc.securebagapi.complaint.infrastructure.dto.request.ComplaintRequest;

import java.util.Optional;

public interface ComplaintService {
    Long createComplaint(ComplaintRequest complaintRequest);
    Optional<Complaint> getComplaintById(Long complaintId);
    Complaint updateComplaintById(Long complaintId, ComplaintRequest complaintRequest);
    void deleteComplaintById(Long complaintId);
}
