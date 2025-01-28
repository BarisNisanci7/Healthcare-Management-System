package com.example.doctorservice.Security.AuthController;

import com.example.doctorservice.Models.Doctor;
import com.example.doctorservice.Security.Jwt.JwtUtil;
import com.example.doctorservice.Services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final DoctorService doctorService;
    private final JwtUtil jwtUtil;

    // Constructor-based dependency injection for DoctorService and JwtUtil
    public AuthController(DoctorService doctorService, JwtUtil jwtUtil) {
        this.doctorService = doctorService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerDoctor(@RequestBody Doctor doctor) {
        doctorService.registerDoctor(doctor);
        return ResponseEntity.ok("Doctor registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Doctor doctor = doctorService.authenticateDoctor(email, password);
        String token = jwtUtil.generateToken(doctor.getEmail(), "ROLE_DOCTOR");
        return ResponseEntity.ok(token);
    }
}
