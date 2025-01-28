package com.example.doctorservice.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private String tcNumber;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
