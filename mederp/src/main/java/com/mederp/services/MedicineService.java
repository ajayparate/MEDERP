
package com.mederp.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mederp.dto.ApiResponse;
import com.mederp.dto.MedicineCreateRequest;
import com.mederp.dto.MedicineResponse;
import com.mederp.dto.MedicineUpdateRequest;
import com.mederp.entities.Medicine;
import com.mederp.enums.MedicineCategory;
import com.mederp.repository.MedicineRepository;
import com.mederp.service.mapper.MedicineMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository repo;

    @Transactional
    public ApiResponse<MedicineResponse> create(MedicineCreateRequest req) {
        if (repo.existsByNameIgnoreCaseAndCategory(req.getName(), req.getCategory())) {
            return ApiResponse.<MedicineResponse>builder()
            .success(false)
            .message("Medicine already exists with same name and category")
            .build();
        }

        Medicine entity = Medicine.builder()
            .name(req.getName().trim())
            .manufacturer(req.getManufacturer().trim())
            .category(req.getCategory())
            .quantity(req.getQuantity())
            .price(req.getPrice())
            .expiryDate(req.getExpiryDate())
            .manufacturingDate(req.getManufacturingDate())
            .available(req.isAvailable())
            .build();

        Medicine saved = repo.save(entity);
        return ApiResponse.<MedicineResponse>builder()
            .success(true)
            .message("Medicine created successfully")
            .data(MedicineMapper.toResponse(saved))
            .build();
        }



    @Transactional(readOnly = true)
    public List<MedicineResponse> list (String nameContains, MedicineCategory category) {
        List<Medicine> meds;

        if (nameContains != null && !nameContains.isBlank() && category != null) {
            meds = repo.findByNameContainingIgnoreCase(nameContains)
                .stream()
                .filter(m -> m.getCategory() == category)
                .toList();
            
        }else if (nameContains != null && !nameContains.isBlank()) {
            meds = repo.findByNameContainingIgnoreCase(nameContains);
        }else if (category != null) {
            meds = repo.findByCategory(category);
            
        }else {
            meds = repo.findAll();
        }
        return  meds.stream()
            .map(MedicineMapper::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public MedicineResponse getById(Long id) {
        Medicine med = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Medicine Not Found"));
        return MedicineMapper.toResponse(med);
    }

    @Transactional
    public ApiResponse<MedicineResponse> update (Long id, MedicineUpdateRequest request) {
        Medicine m = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Medicine Not Found."));

        //to prevent duplicate after update
        boolean duplicate = repo.existsByNameIgnoreCaseAndCategory(request.getName(), request.getCategory())
            && !(m.getName().equalsIgnoreCase(request.getName()) && m.getCategory() == request.getCategory());
        
            if (duplicate) {
                return ApiResponse.<MedicineResponse>builder()
                    .success(false)
                    .message("Another Medicine with same name & category already exists")
                    .build();
                
            }

            m.setName(request.getName().trim());
            m.setCategory(request.getCategory());
            

            Medicine saved = repo.save(m);

            return ApiResponse.<MedicineResponse>builder()
                .success(true)
                .message("Medicine updated successfully")
                .data(MedicineMapper.toResponse(saved))
                .build();
    }

    @Transactional
    public ApiResponse<Void> delete (Long id) {
        if (!repo.existsById(id)) {
            return ApiResponse.<Void>builder()
                .success(false)
                .message("Medicine Not Found")
                .build();
        }
        repo.deleteById(id);

        return ApiResponse.<Void>builder()
            .success(true)
            .message("Medicine deleted successfully")
            .build();
    }

}
