// DTO for transferring prescription data
package com.example.prescriptionservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

// DTO for creating or transferring prescription data
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDTO {

    @NotBlank(message = "Patient TC number cannot be blank.")
    private String patientTc; // Patient's TC (ID)

    private Long doctorId;    // Doctor's ID
    private List<String> medicineNames; // List of medicine names

    // Getters for better encapsulation
    public String getPatientTc() {
        return patientTc;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public List<String> getMedicineNames() {
        return medicineNames;
    }
}
