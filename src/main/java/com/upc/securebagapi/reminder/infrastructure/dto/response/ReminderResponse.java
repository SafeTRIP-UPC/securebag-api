package com.upc.securebagapi.reminder.infrastructure.dto.response;


import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import com.upc.securebagapi.reminder.domain.models.enums.ReminderEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class ReminderResponse {

    private Long reminderId;
    private UserEntity userId;
    private String message;
    private ReminderEnum status;
    private Timestamp createdAt;
}
