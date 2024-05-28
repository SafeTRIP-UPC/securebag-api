package com.upc.securebagapi.complaint.infrastructure.repository;


import com.upc.securebagapi.complaint.domain.models.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

}
