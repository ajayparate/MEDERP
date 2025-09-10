package com.mederp.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mederp.enums.MedicineCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "medicines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Medicine name is required.")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Medicine name is required.")
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    private MedicineCategory category;

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
