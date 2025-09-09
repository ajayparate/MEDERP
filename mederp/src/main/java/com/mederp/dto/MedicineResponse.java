package com.mederp.dto;

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

    private MedicineCategory category;

}
