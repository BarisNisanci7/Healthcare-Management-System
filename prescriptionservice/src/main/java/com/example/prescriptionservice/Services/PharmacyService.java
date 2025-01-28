package com.example.prescriptionservice.Services;

import com.example.prescriptionservice.DTO.PrescriptionDTO;
import com.example.prescriptionservice.Models.Prescription;
import com.example.prescriptionservice.Models.Doctor;
import com.example.prescriptionservice.Models.Medicine;
import com.example.prescriptionservice.Repositories.PrescriptionRepository;
import com.example.prescriptionservice.Repositories.MedicineRepository;
import com.example.prescriptionservice.Repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PharmacyService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;
    private final DoctorRepository doctorRepository;

    public PharmacyService(PrescriptionRepository prescriptionRepository, MedicineRepository medicineRepository, DoctorRepository doctorRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicineRepository = medicineRepository;
        this.doctorRepository = doctorRepository;
    }

    // Submits a prescription using a DTO
    public Prescription submitPrescriptionFromDTO(PrescriptionDTO prescriptionDTO) {
        Doctor doctor = doctorRepository.findById(prescriptionDTO.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID"));

        List<Medicine> medicines = prescriptionDTO.getMedicineNames().stream()
                .map(name -> medicineRepository.findByName(name)
                        .orElseThrow(() -> new IllegalArgumentException("Medicine not found: " + name)))
                .collect(Collectors.toList());

        Prescription prescription = new Prescription();
        prescription.setPatientTc(prescriptionDTO.getPatientTc());
        prescription.setDoctor(doctor);
        prescription.setMedicines(medicines);
        prescription.setComplete(false);

        return prescriptionRepository.save(prescription);
    }

    // Searches medicines by name
    public List<Medicine> searchMedicine(String query) {
        return medicineRepository.findByNameContainingIgnoreCase(query);
    }

    // Searches doctors by name
    public List<Doctor> searchDoctors(String query) {
        return doctorRepository.findByNameContainingIgnoreCase(query);
    }
}
