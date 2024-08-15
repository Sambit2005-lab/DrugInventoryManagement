package com.codexnovas.inventoryManagement.Service;

import com.codexnovas.inventoryManagement.Repository.HospitalRepository;
import com.codexnovas.inventoryManagement.entity.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public Hospital saveHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Optional<Hospital> getHospitalById(Long id) {
        return hospitalRepository.findById(id);
    }

    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }
}
