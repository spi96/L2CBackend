package com.example.spi.localize2socialize.service;

import com.example.spi.localize2socialize.persistence.CalendarRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
@Component
public class ScheduledTasks {
    CalendarRepository calendarRepository;

    public ScheduledTasks(CalendarRepository calendarRepository){
        this.calendarRepository = calendarRepository;
    }

    @Transactional
    @EventListener(ContextRefreshedEvent.class)
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredCalendars(){
        Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        calendarRepository.deleteCalendarsByEndOfSharingIsBefore(currentDate);
    }
}
