package com.example.spi.localize2socialize.service;

import com.example.spi.localize2socialize.dao.AccountRepository;
import com.example.spi.localize2socialize.dao.RelationshipRepository;
import com.example.spi.localize2socialize.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    AccountRepository accountRepository;
    RelationshipRepository relationshipRepository;

    public AccountService(AccountRepository accountRepository,
                          RelationshipRepository relationshipRepository){
        this.accountRepository = accountRepository;
        this.relationshipRepository = relationshipRepository;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public Account saveOrUpdateAccount(Account account) {
        Account entity = null;
        List<Account> result = accountRepository.findByPersonId(account.getPersonId());

        if (result.isEmpty()) {
            entity = accountRepository.save(account);
        } else {
            entity = result.get(0);
            if (!result.get(0).equals(account)) {
                entity.setPersonEmail(account.getPersonEmail());
                entity.setPersonFamilyName(account.getPersonFamilyName());
                entity.setPersonGivenName(account.getPersonGivenName());
                entity.setPersonName(account.getPersonName());
                entity.setEncodedPhoto(account.getEncodedPhoto());
                accountRepository.save(entity);
            }
        }

        return entity;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public List<Account> findAccounts(String personId, String filter) {
        List<Account> result = accountRepository.findAccountsByNamePattern(filter, personId);
        List<Account> sentRequestsByPerson = relationshipRepository.findReceiverAccounts(personId, null);
        List<Account> receivedRequestsByPerson = relationshipRepository.findSenderAccounts(personId, null);
        result.removeIf(a -> sentRequestsByPerson.contains(a) || receivedRequestsByPerson.contains(a));

        return result;
    }

    @Override
    public Account getAccount(String personId){
        Account entity = null;
        List<Account> accounts = accountRepository.findByPersonId(personId);
        if(accounts.isEmpty())
            return entity;
        return accounts.get(0);
    }
}
