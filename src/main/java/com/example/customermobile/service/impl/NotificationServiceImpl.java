package com.example.customermobile.service.impl;

import com.example.customermobile.entity.Notification;
import com.example.customermobile.exception.ResourceNotFoundException;
import com.example.customermobile.payload.NotificationDto;
import com.example.customermobile.payload.NotificationResponse;
import com.example.customermobile.repository.NotificationRepository;
import com.example.customermobile.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Resource
    private NotificationRepository notificationRepository;

    @Resource
    private ModelMapper modelMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, ModelMapper modelMapper) {
        this.notificationRepository = notificationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public NotificationDto createNo(NotificationDto noDto) {
        Notification notification = mapToEntity(noDto);
        Notification newNotification = notificationRepository.save(notification);

        return mapToDto(newNotification);
    }

    @Override
    public NotificationResponse getAllNos(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Notification> notifications = notificationRepository.findAll(pageable);
        List<Notification> notificationList = notifications.getContent();

        List<NotificationDto> content = notificationList.stream().map(this::mapToDto).collect(Collectors.toList());

        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setContent(content);
        notificationResponse.setPageNo(notifications.getNumber());
        notificationResponse.setPageSize(notifications.getSize());
        notificationResponse.setTotalElements(notifications.getTotalElements());
        notificationResponse.setTotalPages(notifications.getTotalPages());
        notificationResponse.setLast(notifications.isLast());

        return notificationResponse;
    }

    @Override
    public NotificationDto getNoById(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification", "id", id));
        return mapToDto(notification);
    }

    @Override
    public NotificationDto updateNo(NotificationDto noDto, Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification", "id", id));

        notification.setContent(noDto.getContent());
        notification.setNotificationType(noDto.getNotificationType());
        notification.setRoute(noDto.getRoute());
        notification.setCreateDate(noDto.getCreateDate());
        notification.setReadStatus(false);

        Notification updateNo = notificationRepository.save(notification);

        return mapToDto(updateNo);
    }

    @Override
    public void deleteNo(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification", "id", id));
        notificationRepository.delete(notification);
    }

    private NotificationDto mapToDto(Notification newNotification) {
        return modelMapper.map(newNotification, NotificationDto.class);
    }

    private Notification mapToEntity(NotificationDto notificationDto) {
        return modelMapper.map(notificationDto, Notification.class);
    }
}
