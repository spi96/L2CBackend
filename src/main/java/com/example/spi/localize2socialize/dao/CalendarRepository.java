package com.example.spi.localize2socialize.dao;

import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByEndOfSharingIsBefore(Date deadline);
    void deleteCalendarsByEndOfSharingIsBefore(Date deadline);
    boolean existsByCalIdAndOwnerAndDisplayName(Long calId, Account owner, String displayName);
    List<Calendar> findAllByParticipantsContains(Account account);
}
