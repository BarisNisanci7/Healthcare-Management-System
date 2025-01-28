// Entity for Prescription
package com.example.prescriptionservice.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "prescriptions") // Maps to the "prescriptions" table in the database
@Data
@NoArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(nullable = false) // Patient's TC number
    private String patientTc;

    @ManyToOne // Many prescriptions can be associated with one doctor
    @JoinColumn(name = "doctor_id", nullable = false) // Foreign key
    private Doctor doctor;

    @Column(nullable = false) // Flag to indicate if the prescription is complete
    private boolean isComplete;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}) // Many-to-Many relationship with Medicine
    @JoinTable(
            name = "prescription_medicines",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> medicines;
}
