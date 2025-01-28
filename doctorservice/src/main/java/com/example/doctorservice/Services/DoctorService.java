package com.example.doctorservice.Services;

import com.example.doctorservice.Models.Doctor;
import com.example.doctorservice.Repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Registers a new doctor, checks for unique email before saving
    public Doctor registerDoctor(Doctor doctor) {
        if (doctorRepository.findByEmail(doctor.getEmail()) != null) {
            throw new IllegalStateException("Email is already in use!");
        }
        return doctorRepository.save(doctor);
    }

    // Retrieves a doctor by their ID
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Authenticates a doctor based on email and password
    public Doctor authenticateDoctor(String email, String password) {
        Doctor doctor = doctorRepository.findByEmail(email);
        if (doctor == null || !doctor.getPassword().equals(password)) {
            throw new IllegalStateException("Invalid credentials!");
        }
        return doctor;
    }
}