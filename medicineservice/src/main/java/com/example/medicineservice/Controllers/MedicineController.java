package com.example.medicineservice.Controllers;

import com.example.medicineservice.Models.Medicine;
import com.example.medicineservice.Repositories.MedicineRepository;
import com.example.medicineservice.Services.MedicineImportService;
import com.example.medicineservice.Services.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    private final MedicineService medicineService;
    private final MedicineImportService medicineImportService;
    private final MedicineRepository medicineRepository;

    public MedicineController(MedicineService medicineService, MedicineImportService medicineImportService, MedicineRepository medicineRepository) {
        this.medicineService = medicineService;
        this.medicineImportService = medicineImportService;
        this.medicineRepository = medicineRepository;
    }

    // AUTOCOMPLETE FOR MEDICINE NAME
    // Endpoint to search medicines by a query string
    @GetMapping("/search")
    public ResponseEntity<List<String>> searchMedicines(@RequestParam String query) {
        List<Medicine> medicines = medicineService.searchMedicines(query);
        List<String> medicineNames = medicines.stream()
                .map(Medicine::getName)
                .collect(Collectors.toList());
        return ResponseEntity.ok(medicineNames);
    }

    // Endpoint to retrieve all medicines in the database
    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        System.out.println(medicines); // Konsola yazdırarak kontrol edin
        return ResponseEntity.ok(medicines);
    }


    // Endpoint to upload and import medicines from an Excel file
    @PostMapping("/importExcel")
    public ResponseEntity<String> uploadAndImportMedicines(@RequestParam("filePath") MultipartFile filePath) {
        if (filePath.isEmpty()) {
            return ResponseEntity.badRequest().body("Excel file is empty!");
        }

        try {
            // Save the uploaded file temporarily
            Path tempFile = Files.createTempFile("uploaded-", filePath.getOriginalFilename());
            Files.write(tempFile, filePath.getBytes());

            // Process the Excel file
            medicineImportService.importMedicinesFromExcel(tempFile.toString());

            // Delete the temporary file after processing
            Files.delete(tempFile);

            return ResponseEntity.ok("Medicines imported successfully from Excel.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error importing medicines: " + e.getMessage());
        }
    }


    // Endpoint to add a single medicine record
    @PostMapping
    public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine) {
        return ResponseEntity.ok(medicineService.addMedicine(medicine));
    }
}
