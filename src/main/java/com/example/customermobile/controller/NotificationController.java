package com.example.customermobile.controller;

import com.example.customermobile.payload.NotificationDto;
import com.example.customermobile.payload.NotificationResponse;
import com.example.customermobile.service.NotificationService;
import com.example.customermobile.untils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class NotificationController {
    @Resource
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/notification")
    public ResponseEntity<NotificationDto> createNotification(@Valid @RequestBody NotificationDto notificationDto) {
        return new ResponseEntity<>(notificationService.createNo(notificationDto), HttpStatus.CREATED);
    }

    @GetMapping("/notifications")
    public NotificationResponse getAllNotifications(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return notificationService.getAllNos(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/notifications/{id}")
    public ResponseEntity<NotificationDto> getNotificationById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(notificationService.getNoById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/notifications/{id}")
    public ResponseEntity<NotificationDto> updateNotification(@Valid @RequestBody NotificationDto notificationDto, @PathVariable(name = "id") Long id) {
        NotificationDto responseNotification = notificationService.updateNo(notificationDto, id);

        return new ResponseEntity<>(responseNotification, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable(name = "id") Long id) {
        notificationService.deleteNo(id);

        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }
}
