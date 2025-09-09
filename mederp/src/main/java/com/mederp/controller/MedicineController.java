package com.mederp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mederp.dto.ApiResponse;
import com.mederp.dto.MedicineCreateRequest;
import com.mederp.dto.MedicineResponse;
import com.mederp.dto.MedicineUpdateRequest;
import com.mederp.enums.MedicineCategory;
import com.mederp.services.MedicineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
// @CrossOrigin("http://http://localhost:5173")
public class MedicineController {

    private final MedicineService service;

    @PostMapping
    public ResponseEntity<ApiResponse<MedicineResponse>> create (@Valid @RequestBody MedicineCreateRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<MedicineResponse>> list (@RequestParam (required = false, name = "name") String nameContains,
                                                        @RequestParam(required = false) MedicineCategory category) {
                    return ResponseEntity.ok(service.list(nameContains, category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineResponse> get (@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicineResponse>> update (@PathVariable Long id,
                @Valid @RequestBody MedicineUpdateRequest req) 

    {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete (@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
