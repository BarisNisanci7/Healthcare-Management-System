// Package for controllers
package com.example.prescriptionservice.Controllers;

import com.example.prescriptionservice.DTO.PrescriptionDTO;
import com.example.prescriptionservice.Models.Doctor;
import com.example.prescriptionservice.Models.Prescription;
import com.example.prescriptionservice.Models.Medicine;
import com.example.prescriptionservice.Services.PharmacyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST Controller for managing pharmacy-related operations
@RestController
@RequestMapping("/api/pharmacy") // Base path for pharmacy-related APIs
public class PharmacyController {

    private final PharmacyService pharmacyService;

    // Constructor-based dependency injection
    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    // Endpoint to submit a prescription
    @PostMapping("/submitPrescription")
    public ResponseEntity<Prescription> submitPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        Prescription savedPrescription = pharmacyService.submitPrescriptionFromDTO(prescriptionDTO);
        return ResponseEntity.ok(savedPrescription);
    }

    // Endpoint to search for medicines by name
    @GetMapping("/searchMedicine")
    public ResponseEntity<List<Medicine>> searchMedicine(@RequestParam String query) {
        List<Medicine> medicines = pharmacyService.searchMedicine(query);
        return ResponseEntity.ok(medicines);
    }

    // Endpoint to search for doctors by name
    @GetMapping("/searchDoctor")
    public ResponseEntity<List<Doctor>> searchDoctor(@RequestParam String query) {
        List<Doctor> doctors = pharmacyService.searchDoctors(query);
        return ResponseEntity.ok(doctors);
    }
}
