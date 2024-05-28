package com.upc.securebagapi.reminder.infrastructure.dto.request;


import com.upc.securebagapi.auth.domain.models.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class ReminderRequest {

    @NotBlank(message = "Message is required")
    @Positive(message = "Message must be positive")
    private UserEntity userEntity;

    @NotBlank(message = "Message is required")
    private String message;
}
