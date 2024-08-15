package com.codexnovas.inventoryManagement.Controller;

import com.codexnovas.inventoryManagement.Service.MedicineService;
import com.codexnovas.inventoryManagement.entity.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
        Medicine createdMedicine = medicineService.saveMedicine(medicine);
        return ResponseEntity.ok(createdMedicine);
    }

    @GetMapping
    @RequestMapping("/all")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }

    @GetMapping("/{id}")
    @RequestMapping("/get/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        Optional<Medicine> medicine = medicineService.getMedicineById(id);
        return medicine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @RequestMapping("/update/{id}")
    public ResponseEntity<Medicine> updateMedicine(
            @PathVariable Long id, @RequestBody Medicine updatedMedicine) {
        Optional<Medicine> existingMedicine = medicineService.getMedicineById(id);

        if (existingMedicine.isPresent()) {
            Medicine medicine = existingMedicine.get();
            medicine.setSerialNumber(updatedMedicine.getSerialNumber());
            medicine.setName(updatedMedicine.getName());
            medicine.setBatchNumber(updatedMedicine.getBatchNumber());
            medicine.setQuantity(updatedMedicine.getQuantity());
            medicine.setExpiryDate(updatedMedicine.getExpiryDate());
            medicine.setManufacturedDate(updatedMedicine.getManufacturedDate());
            medicine.setSupplierName(updatedMedicine.getSupplierName());
            medicine.setPrice(updatedMedicine.getPrice());

            medicineService.saveMedicine(medicine);
            return ResponseEntity.ok(medicine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @RequestMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }
}