package com.codexnovas.inventoryManagement.Service;

import com.codexnovas.inventoryManagement.Repository.MedicineRepository;
import com.codexnovas.inventoryManagement.entity.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Optional<Medicine> getMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }
}
