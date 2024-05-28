package com.upc.securebagapi.complaint.infrastructure.controller;


import com.upc.securebagapi.complaint.domain.models.entity.Complaint;
import com.upc.securebagapi.complaint.domain.services.ComplaintService;
import com.upc.securebagapi.complaint.infrastructure.dto.request.ComplaintRequest;
import com.upc.securebagapi.complaint.infrastructure.dto.response.ComplaintResponse;
import com.upc.securebagapi.shared.domain.exceptions.ResourceNotFoundException;
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
@RequestMapping(value = "/api/v1/complaints", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Complaints", description = "Complaints API")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final ModelMapper modelMapper;

    @Transactional
    @PostMapping(value = "")
    public ResponseEntity<ComplaintResponse> create(@Valid @RequestBody ComplaintRequest complaintRequest) {
        Long complaintId = complaintService.createComplaint(complaintRequest);
        Complaint complaintCreated = complaintService.getComplaintById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found with id: " + complaintId));
        ComplaintResponse complaintResponse = modelMapper.map(complaintCreated, ComplaintResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(complaintResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<ComplaintResponse> get(@PathVariable Long id) {
        Complaint complaintFound = complaintService.getComplaintById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found with id: " + id));
        ComplaintResponse complaintResponse = modelMapper.map(complaintFound, ComplaintResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(complaintResponse);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<ComplaintResponse> update(@PathVariable Long id, @Valid @RequestBody ComplaintRequest complaintRequest) {
        Complaint complaintUpdated = complaintService.updateComplaintById(id, complaintRequest);
        ComplaintResponse complaintResponse = modelMapper.map(complaintUpdated, ComplaintResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(complaintResponse);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        complaintService.deleteComplaintById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
