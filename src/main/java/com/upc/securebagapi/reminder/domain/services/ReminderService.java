package com.upc.securebagapi.reminder.domain.services;


import com.upc.securebagapi.reminder.domain.models.entity.Reminder;
import com.upc.securebagapi.reminder.infrastructure.dto.request.ReminderRequest;

import java.util.Optional;

public interface ReminderService {
    Long createReminder(ReminderRequest reminderRequest);
    Optional<Reminder> getReminderById(Long reminderId);
    Reminder updateReminder(Long reminderId, ReminderRequest reminderRequest);
    void deleteReminder(Long reminderId);
}
