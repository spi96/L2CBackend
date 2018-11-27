package com.example.spi.localize2socialize.dao;

import com.example.spi.localize2socialize.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByPersonEmail (String email);

    List<Account> findByPersonId (String personId);

    List<Account> findAllByPersonIdIn(List<String> personIdList);

    @Query("SELECT a FROM Account a WHERE a.id <> ?2 AND a.personName LIKE %?1% ORDER BY a.personName")
    List<Account> findAccountsByNamePattern(String namePattern, Long id);
}
