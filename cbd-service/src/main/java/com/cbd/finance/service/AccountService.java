package com.cbd.finance.service;

import com.cbd.finance.exception.EntityNotFoundException;
import com.cbd.finance.model.dto.BankAccount;
import com.cbd.finance.model.entity.BankAccountEntity;
import com.cbd.finance.model.mapper.BankAccountMapper;
import com.cbd.finance.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private BankAccountMapper bankAccountMapper = new BankAccountMapper();

   @Autowired 
   BankAccountRepository bankAccountRepository;

    public BankAccount readBankAccount(String accountNumber) {
        BankAccountEntity entity = bankAccountRepository.findByNumber(accountNumber).orElseThrow(EntityNotFoundException::new);
        return bankAccountMapper.convertToDto(entity);
    }

}
