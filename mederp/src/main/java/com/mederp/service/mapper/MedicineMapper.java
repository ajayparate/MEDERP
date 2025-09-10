package com.mederp.service.mapper;

import com.mederp.dto.MedicineResponse;
import com.mederp.entities.Medicine;

public class MedicineMapper {

    public static MedicineResponse toResponse (Medicine m) {
        return MedicineResponse.builder()
            .id(m.getId())
            .name(m.getName())
            .category(m.getCategory())
            .build();
    }

//     public static MedicineResponse toResponse1(Medicine med) {
//     return MedicineResponse.builder()
//       .id(med.getId())
//       .name(med.getName())
//       .manufacturer(med.getManufacturer())
//       .category(med.getCategory())
//       .quantity(med.getQuantity())
//       .price(med.getPrice())
//       .expiryDate(med.getExpiryDate())
//       .manufacturingDate(med.getManufacturingDate())
//       .available(med.isAvailable())
//       .build();
//   }

}
