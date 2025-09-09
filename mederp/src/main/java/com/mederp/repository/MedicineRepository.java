package com.mederp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mederp.entities.Medicine;
import com.mederp.enums.MedicineCategory;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    boolean existsByNameIgnoreCaseAndCategory(String name, MedicineCategory category);
    Optional<Medicine> findByNameIgnoreCaseAndCategory(String name, MedicineCategory category);

    // simple filtering helpers
    List<Medicine> findByNameContainingIgnoreCase(String name);
    List<Medicine> findByCategory(MedicineCategory category);

}
