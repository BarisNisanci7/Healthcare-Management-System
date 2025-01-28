// DTO for transferring medicine data
package com.example.prescriptionservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO representing a medicine
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDTO {
    private String name; // Medicine name
    private double price; // Medicine price
}
