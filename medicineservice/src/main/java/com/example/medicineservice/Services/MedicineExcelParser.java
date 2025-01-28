package com.example.medicineservice.Services;

import com.example.medicineservice.Models.Medicine;
import org.springframework.stereotype.Component;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.example.medicineservice.Models.Medicine;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Component responsible for parsing Excel files to extract medicine data
@Component
public class MedicineExcelParser {

    // Reads medicines from an Excel file
    public List<Medicine> readMedicinesFromExcel(String filePath) {
        List<Medicine> medicines = new ArrayList<>();
        Path path = Paths.get(filePath);

        // Check if the file exists and is readable
        if (!Files.exists(path) || !Files.isReadable(path)) {
            throw new RuntimeException("File does not exist or is not readable: " + filePath);
        }

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();

            for (int i = 3; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null || isRowEmpty(row)) {
                    continue;
                }

                Medicine medicine = new Medicine();
                medicine.setName(getCellValue(row, 0));

                String priceValue = getCellValue(row, 7);
                if (!priceValue.isEmpty()) {
                    try {
                        medicine.setPrice(Double.parseDouble(priceValue));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid price format at row " + (i + 1) + ": " + priceValue);
                        continue;
                    }
                } else {
                    medicine.setPrice(0.0);
                }

                medicines.add(medicine);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file", e);
        }

        return medicines;
    }


    private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            default -> "";
        };
    }

    private boolean isRowEmpty(Row row) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
