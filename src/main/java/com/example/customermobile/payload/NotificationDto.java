package com.example.customermobile.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Data
public class NotificationDto {
    private Long notificationId;

    @NotEmpty
    private String content;

    @NotEmpty
    private Date createDate;

    @NotEmpty
    private int notificationType;

    @NotEmpty
    private boolean readStatus;

    @NotEmpty
    private String route;
}
