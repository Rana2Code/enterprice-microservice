package com.cbd.finance.controller;

import com.cbd.finance.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/cbd/account")
@RequiredArgsConstructor
public class AccountController {

	@Autowired  
	AccountService accountService;

    @GetMapping("/bank-account/{account_number}")
    public ResponseEntity getBankAccount(@PathVariable("account_number") String accountNumber) {
//        log.info("Reading account number by ID {}", accountNumber);
        return ResponseEntity.ok(accountService.readBankAccount(accountNumber));
    }

}
