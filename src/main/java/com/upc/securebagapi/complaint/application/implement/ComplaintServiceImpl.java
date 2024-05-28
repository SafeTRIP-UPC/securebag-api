package com.upc.securebagapi.complaint.application.implement;


import com.upc.securebagapi.auth.infrastructure.repository.UserRepository;
import com.upc.securebagapi.complaint.domain.models.entity.Complaint;
import com.upc.securebagapi.complaint.domain.services.ComplaintService;
import com.upc.securebagapi.complaint.infrastructure.dto.request.ComplaintRequest;
import com.upc.securebagapi.complaint.infrastructure.repository.ComplaintRepository;
import com.upc.securebagapi.insurance.infrastructure.repository.PolicyRepository;
import com.upc.securebagapi.shared.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long createComplaint(ComplaintRequest complaintRequest) {
        if (userRepository.existsByUserId(complaintRequest.getUserEntity().getUserId())) {
            throw new ValidationException("Complaint already has a User created with id: " + complaintRequest.getUserEntity().getUserId());
        }
        if (policyRepository.existsByPolicyId(complaintRequest.getPolicyEntity().getPolicyId())) {
            throw new ValidationException("Complaint already has a Policy created with id: " + complaintRequest.getPolicyEntity().getPolicyId());
        }
        Complaint complaint = new Complaint();
        modelMapper.map(complaintRequest, complaint);

        return complaintRepository.save(complaint).getComplaintId();
    }

    @Override
    public Optional<Complaint> getComplaintById(Long complaintId) {
        return complaintRepository.findById(complaintId);
    }

    @Override
    public Complaint updateComplaintById(Long id, ComplaintRequest complaintRequest) {
        Complaint complaintToUpdate = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found with id: " + id));
        modelMapper.map(complaintRequest, complaintToUpdate);

        return complaintRepository.save(complaintToUpdate);
    }

    @Override
    public void deleteComplaintById(Long id) {
        if (!complaintRepository.existsById(id)) {
            throw new ResourceNotFoundException("Complaint not found with id: " + id);
        }
        complaintRepository.deleteById(id);
    }
}
