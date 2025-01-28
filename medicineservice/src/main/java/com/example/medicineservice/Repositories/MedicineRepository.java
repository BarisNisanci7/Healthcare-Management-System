package com.example.medicineservice.Repositories;

import com.example.medicineservice.Models.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MedicineRepository extends MongoRepository<Medicine, String> {
    // Custom query to find medicines by name (case insensitive)
    List<Medicine> findByNameContainingIgnoreCase(String name);
}
