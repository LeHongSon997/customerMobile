package com.example.customermobile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "notificationType")
    private int notificationType;

    @Column(name = "readStatus")
    private boolean readStatus;

    @Column(name = "route")
    private String route;
}
