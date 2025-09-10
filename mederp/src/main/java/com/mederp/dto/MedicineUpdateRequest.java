package com.mederp.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @NotBlank(message = "Medicine name is required.")
    private String manufacturer;

    // @Enumerated(EnumType.STRING)
    // private MedicineCategory category;

    @NotBlank(message = "Quantity is required.")
    private int quantity;

    private double price;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Expiry Date is required.")
    private LocalDate expiryDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Manufacting Date is required.")
    private LocalDate manufacturingDate;

    private boolean available; // this check the medicine is available in the market or not (illegal medicine)

}
