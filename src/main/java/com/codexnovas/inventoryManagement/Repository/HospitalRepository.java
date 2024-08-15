package com.codexnovas.inventoryManagement.Repository;

import com.codexnovas.inventoryManagement.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
