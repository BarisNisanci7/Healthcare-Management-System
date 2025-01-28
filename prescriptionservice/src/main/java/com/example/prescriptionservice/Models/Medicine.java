// Entity for Medicine
package com.example.prescriptionservice.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicines") // Maps to the "medicines" table in the database
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(nullable = false, unique = true) // Medicine name must be unique
    private String name;

    @Column(nullable = false) // Price cannot be null
    private double price;
}
