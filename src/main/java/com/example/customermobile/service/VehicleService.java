package com.example.customermobile.service;

import com.example.customermobile.payload.VehicleDto;
import com.example.customermobile.payload.VehicleResponse;

public interface VehicleService {
    VehicleDto createVe(VehicleDto veDto);

    VehicleResponse getAllVes(int pageNo, int pageSize, String sortBy, String sortDir);

    VehicleDto getVeById(Long id);

    VehicleDto updateVe(VehicleDto veDto, Long id);

    void deleteVe(Long id);
}
