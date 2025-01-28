package com.example.prescriptionservice.Repositories;

import com.example.prescriptionservice.Models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByIsComplete(boolean isComplete); // Find prescriptions by completion status
}
