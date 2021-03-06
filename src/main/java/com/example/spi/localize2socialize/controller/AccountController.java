package com.example.spi.localize2socialize.controller;

import com.example.spi.localize2socialize.domain.Account;
import com.example.spi.localize2socialize.dto.SearchRequest;
import com.example.spi.localize2socialize.service.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    IAccountService accountService;

    public AccountController(IAccountService accountService){
        this.accountService = accountService;
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Long>> saveOrUpdateAccount(@RequestBody Account account) {
         Account entity = accountService.saveOrUpdateAccount(account);

        Map<String, Long> response = new HashMap<>();
        response.put("accountId", entity.getId());
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public Map<String, List<Account>> findAccounts(@RequestBody SearchRequest searchRequest) {
        List<Account> result =
                accountService.findAccounts(searchRequest.getCallerPerson().getPersonId(), searchRequest.getFilter());

        Map<String, List<Account>> resultSet = new HashMap<>();
        resultSet.put("accounts", result);

        return resultSet;
    }
}
