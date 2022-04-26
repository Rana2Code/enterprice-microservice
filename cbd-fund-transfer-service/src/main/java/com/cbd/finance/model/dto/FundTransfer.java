package com.cbd.finance.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundTransfer {
    private Long id;
    private String transactionReference;
    private String status;
    private String fromAccount;
    private String toAccount;
    private String currency;
    private BigDecimal amount;
    private String creditDescription;
    private String debitDescription;
}
