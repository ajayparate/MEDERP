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
}
