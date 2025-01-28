package com.example.prescriptionservice.Repositories;

import com.example.prescriptionservice.Models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByName(String name); // Find medicine by exact name
    List<Medicine> findByNameContainingIgnoreCase(String query); // Search medicines by partial name
}
