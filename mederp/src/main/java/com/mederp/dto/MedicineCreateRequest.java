package com.mederp.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mederp.enums.MedicineCategory;

// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MedicineCreateRequest {

    @NotBlank(message = "Medicine name is required")
    private String name;

    // @NotBlank(message = "Category is required")
    private MedicineCategory category;

    @NotBlank(message = "Medicine name is required.")
    private String manufacturer;

    // @Enumerated(EnumType.STRING)
    // private MedicineCategory category;

    @NotNull(message = "Quantity is required.")
    private int quantity;

    private double price;

    @JsonFormat(pattern = "yyyy-MM-dd")
    // @NotBlank(message = "Expiry Date is required.")
    private LocalDate expiryDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    // @NotBlank(message = "Manufacting Date is required.")
    private LocalDate manufacturingDate;

    private boolean available; // this check the medicine is available in the market or not (illegal medicine)


}
