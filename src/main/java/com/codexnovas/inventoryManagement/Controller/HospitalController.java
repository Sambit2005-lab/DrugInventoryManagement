package com.codexnovas.inventoryManagement.Controller;

import com.codexnovas.inventoryManagement.Service.HospitalService;
import com.codexnovas.inventoryManagement.entity.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        Hospital createdHospital = hospitalService.saveHospital(hospital);
        return ResponseEntity.ok(createdHospital);
    }

    @GetMapping
    @RequestMapping("/all")
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return ResponseEntity.ok(hospitals);
    }

    @GetMapping("/{id}")
    @RequestMapping("/get/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id) {
        Optional<Hospital> hospital = hospitalService.getHospitalById(id);
        return hospital.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @RequestMapping("/update/{id}")
    public ResponseEntity<Hospital> updateHospital(
            @PathVariable Long id, @RequestBody Hospital updatedHospital) {
        Optional<Hospital> existingHospital = hospitalService.getHospitalById(id);

        if (existingHospital.isPresent()) {
            Hospital hospital = existingHospital.get();
            hospital.setHospitalName(updatedHospital.getHospitalName());
            hospital.setHospitalId(updatedHospital.getHospitalId());

            hospitalService.saveHospital(hospital);
            return ResponseEntity.ok(hospital);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @RequestMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
        return ResponseEntity.noContent().build();
    }
}

