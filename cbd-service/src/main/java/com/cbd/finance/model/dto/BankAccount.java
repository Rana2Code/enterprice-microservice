package com.cbd.finance.model.dto;

import com.cbd.finance.model.AccountStatus;
import com.cbd.finance.model.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankAccount {

    private Long id;
    private String number;
    private AccountType type;
    private AccountStatus status;
    private BigDecimal availableBalance;
    private BigDecimal actualBalance;
    private User user;

}
