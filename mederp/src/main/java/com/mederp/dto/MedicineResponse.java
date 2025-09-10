package com.mederp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mederp.enums.MedicineCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;

// import org.hibernate.annotations.SecondaryRow;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineResponse {
    private Long id;
    private String name;
    private String manufacturer;
    private MedicineCategory category;
    private Integer quantity;
    private BigDecimal price;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufacturingDate;

    private Boolean available;


}
