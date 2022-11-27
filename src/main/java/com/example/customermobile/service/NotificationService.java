package com.example.customermobile.service;

import com.example.customermobile.payload.NotificationDto;
import com.example.customermobile.payload.NotificationResponse;

public interface NotificationService {
    NotificationDto createNo(NotificationDto noDto);

    NotificationResponse getAllNos(int pageNo, int pageSize, String sortBy, String sortDir);

    NotificationDto getNoById(Long id);

    NotificationDto updateNo(NotificationDto noDto, Long id);

    void deleteNo(Long id);
}
