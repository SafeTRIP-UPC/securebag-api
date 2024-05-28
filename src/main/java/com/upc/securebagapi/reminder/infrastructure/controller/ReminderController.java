package com.upc.securebagapi.reminder.infrastructure.controller;


import com.upc.securebagapi.reminder.domain.models.entity.Reminder;
import com.upc.securebagapi.reminder.domain.services.ReminderService;
import com.upc.securebagapi.reminder.infrastructure.dto.request.ReminderRequest;
import com.upc.securebagapi.reminder.infrastructure.dto.response.ReminderResponse;
import com.upc.securebagapi.shared.domain.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/reminder", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reminder", description = "Reminder API")
public class ReminderController {

    private final ReminderService reminderService;
    private final ModelMapper modelMapper;

    @Transactional
    @PostMapping(value = "")
    public ResponseEntity<ReminderResponse> create(@Valid @RequestBody ReminderRequest reminderRequest) {
        Long reminderId = reminderService.createReminder(reminderRequest);
        Reminder reminderCreated = reminderService.getReminderById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id: " + reminderId));
        ReminderResponse reminderResponse = modelMapper.map(reminderCreated, ReminderResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(reminderResponse);
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity<ReminderResponse> get(@PathVariable Long id) {
        Reminder reminderFound = reminderService.getReminderById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id: " + id));
        ReminderResponse reminderResponse = modelMapper.map(reminderFound, ReminderResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(reminderResponse);
    }

    @Transactional
    @PutMapping(value = "/{id}")
    public ResponseEntity<ReminderResponse> update(@PathVariable Long id, @Valid @RequestBody ReminderRequest reminderRequest) {
        Reminder reminderUpdated = reminderService.updateReminder(id, reminderRequest);
        ReminderResponse reminderResponse = modelMapper.map(reminderUpdated, ReminderResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(reminderResponse);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reminderService.deleteReminder(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
