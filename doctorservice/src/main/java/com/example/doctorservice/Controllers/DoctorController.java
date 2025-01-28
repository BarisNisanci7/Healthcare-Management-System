package com.example.doctorservice.Controllers;

import com.example.doctorservice.Models.Doctor;
import com.example.doctorservice.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/register")
    public ResponseEntity<Doctor> registerDoctor(@RequestBody Doctor doctor) {
        // Registers a new doctor and returns the saved doctor object
        Doctor savedDoctor = doctorService.registerDoctor(doctor);
        return ResponseEntity.ok(savedDoctor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Doctor>> getDoctorById(@PathVariable Long id) {
        // Fetches doctor details based on the provided ID
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<Doctor> login(@RequestParam String email, @RequestParam String password) {
        // Authenticates a doctor and returns their details
        Doctor authenticatedDoctor = doctorService.authenticateDoctor(email, password);
        return ResponseEntity.ok(authenticatedDoctor);
    }
}
