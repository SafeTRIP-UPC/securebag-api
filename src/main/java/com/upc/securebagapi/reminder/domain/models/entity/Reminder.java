package com.upc.securebagapi.reminder.domain.models.entity;


import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import com.upc.securebagapi.reminder.domain.models.enums.ReminderEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reminder")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_id", nullable = false, updatable = false)
    private Long reminderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

    @Column(name = "message", nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 7)
    private ReminderEnum status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        if (status == null && createdAt == null) {
            status = ReminderEnum.PENDING;
            createdAt = new Timestamp(System.currentTimeMillis());
        }
    }
}
