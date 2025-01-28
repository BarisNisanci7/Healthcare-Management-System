package com.example.medicineservice.Services;

import com.example.medicineservice.Models.Medicine;
import com.example.medicineservice.Repositories.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineImportService {

    private final MedicineExcelParser medicineExcelParser;
    private final MedicineRepository medicineRepository;

    public MedicineImportService(MedicineExcelParser medicineExcelParser, MedicineRepository medicineRepository) {
        this.medicineExcelParser = medicineExcelParser;
        this.medicineRepository = medicineRepository;
    }

    // Imports medicines from an Excel file
    public void importMedicinesFromExcel(String filePath) {
        List<Medicine> medicines = medicineExcelParser.readMedicinesFromExcel(filePath);

        // Save each medicine to the database
        for (Medicine medicine : medicines) {
            medicineRepository.save(medicine);
        }
    }
}
