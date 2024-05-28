package com.upc.securebagapi.reminder.application.implement;


import com.upc.securebagapi.auth.infrastructure.repository.UserRepository;
import com.upc.securebagapi.reminder.domain.models.entity.Reminder;
import com.upc.securebagapi.reminder.domain.services.ReminderService;
import com.upc.securebagapi.reminder.infrastructure.dto.request.ReminderRequest;
import com.upc.securebagapi.reminder.infrastructure.repository.ReminderRepository;
import com.upc.securebagapi.shared.domain.exceptions.ResourceNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long createReminder(ReminderRequest reminderRequest) {
        if (userRepository.existsByUserId(reminderRequest.getUserEntity().getUserId())) {
            throw new ValidationException("Reminder already has a User created with id: " + reminderRequest.getUserEntity().getUserId());
        }
        Reminder reminder = new Reminder();
        modelMapper.map(reminderRequest, reminder);

        return reminderRepository.save(reminder).getReminderId();
    }

    @Override
    public Optional<Reminder> getReminderById(Long reminderId) {
        return reminderRepository.findById(reminderId);
    }

    @Override
    public Reminder updateReminder(Long reminderId, ReminderRequest reminderRequest) {
        Reminder reminderToUpdate = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id: " + reminderId));
        modelMapper.map(reminderRequest, reminderToUpdate);

        return reminderRepository.save(reminderToUpdate);
    }

    @Override
    public void deleteReminder(Long reminderId) {
        if (!reminderRepository.existsById(reminderId)) {
            throw new ResourceNotFoundException("Reminder not found with id: " + reminderId);
        }
        reminderRepository.deleteById(reminderId);
    }
}
