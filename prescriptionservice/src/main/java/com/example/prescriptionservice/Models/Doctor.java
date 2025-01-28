// Entity for Doctor
package com.example.prescriptionservice.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "doctors") // Maps to the "doctors" table in the database
@Data
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(nullable = false, unique = true) // Name must be unique
    private String name;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL) // Relationship with Prescription
    private List<Prescription> prescriptions;
}
