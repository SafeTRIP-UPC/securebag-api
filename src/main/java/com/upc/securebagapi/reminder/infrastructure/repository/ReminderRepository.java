package com.upc.securebagapi.reminder.infrastructure.repository;


import com.upc.securebagapi.reminder.domain.models.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
