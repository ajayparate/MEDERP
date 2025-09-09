package com.mederp.dto;

import com.mederp.enums.MedicineCategory;
// import com.mederp.repository.MedicineRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MedicineUpdateRequest {

    @NotBlank(message = "Medicine name is required")
    private String name;

    @NotNull(message = "Category is required")
    private MedicineCategory category;

}
