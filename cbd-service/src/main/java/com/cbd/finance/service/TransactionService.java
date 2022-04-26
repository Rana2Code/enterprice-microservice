package com.cbd.finance.service;

import com.cbd.finance.exception.EntityNotFoundException;
import com.cbd.finance.exception.GlobalErrorCode;
import com.cbd.finance.exception.InsufficientFundsException;
import com.cbd.finance.model.TransactionType;
import com.cbd.finance.model.dto.BankAccount;
import com.cbd.finance.model.dto.request.FundTransferRequest;
import com.cbd.finance.model.dto.response.FundTransferResponse;
import com.cbd.finance.model.entity.BankAccountEntity;
import com.cbd.finance.model.entity.TransactionEntity;
import com.cbd.finance.repository.BankAccountRepository;
import com.cbd.finance.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

	@Autowired  
	AccountService accountService;
	
	@Autowired  
	BankAccountRepository bankAccountRepository;
	
	@Autowired  
	TransactionRepository transactionRepository;

    public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest) {

        BankAccount fromBankAccount = accountService.readBankAccount(fundTransferRequest.getFromAccount());
        BankAccount toBankAccount = accountService.readBankAccount(fundTransferRequest.getToAccount());

        //validating account balances
        validateBalance(fromBankAccount, fundTransferRequest.getAmount());

        String transactionId = internalFundTransfer(fromBankAccount, toBankAccount, fundTransferRequest.getAmount());
        return FundTransferResponse.builder().message("Transaction successfully completed").transactionId(transactionId).build();

    }

    private void validateBalance(BankAccount bankAccount, BigDecimal amount) {
        if (bankAccount.getActualBalance().compareTo(BigDecimal.ZERO) < 0 || bankAccount.getActualBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in the account " + bankAccount.getNumber(), GlobalErrorCode.INSUFFICIENT_FUNDS);
        }
    }

    public String internalFundTransfer(BankAccount fromBankAccount, BankAccount toBankAccount, BigDecimal amount) {

        String transactionId = UUID.randomUUID().toString();

        BankAccountEntity fromBankAccountEntity = bankAccountRepository.findByNumber(fromBankAccount.getNumber()).orElseThrow(EntityNotFoundException::new);
        BankAccountEntity toBankAccountEntity = bankAccountRepository.findByNumber(toBankAccount.getNumber()).orElseThrow(EntityNotFoundException::new);

        fromBankAccountEntity.setActualBalance(fromBankAccountEntity.getActualBalance().subtract(amount));
        fromBankAccountEntity.setAvailableBalance(fromBankAccountEntity.getActualBalance().subtract(amount));
        bankAccountRepository.save(fromBankAccountEntity);

        transactionRepository.save(TransactionEntity.builder().transactionType(TransactionType.FUND_TRANSFER)
                .referenceNumber(toBankAccountEntity.getNumber())
                .transactionId(transactionId)
                .account(fromBankAccountEntity).amount(amount.negate()).build());

        toBankAccountEntity.setActualBalance(toBankAccountEntity.getActualBalance().add(amount));
        toBankAccountEntity.setAvailableBalance(toBankAccountEntity.getActualBalance().add(amount));
        bankAccountRepository.save(toBankAccountEntity);

        transactionRepository.save(TransactionEntity.builder().transactionType(TransactionType.FUND_TRANSFER)
                .referenceNumber(toBankAccountEntity.getNumber())
                .transactionId(transactionId)
                .account(toBankAccountEntity).amount(amount).build());

        return transactionId;

    }

}
