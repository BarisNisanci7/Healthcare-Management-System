package com.example.prescriptionservice.Repositories;

import com.example.prescriptionservice.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByName(String name); // Find a doctor by name
    List<Doctor> findByNameContainingIgnoreCase(String query); // Search doctors by partial name
}
