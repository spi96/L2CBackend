package com.example.spi.localize2socialize.persistence;

import com.example.spi.localize2socialize.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findByPersonId (String personId);
    List<Account> findAllByPersonIdIn(List<String> personIdList);
    List<Account> findAllByIdNotAndPersonNameContainingOrderByPersonFamilyName(Long id, String namePattern);

}
