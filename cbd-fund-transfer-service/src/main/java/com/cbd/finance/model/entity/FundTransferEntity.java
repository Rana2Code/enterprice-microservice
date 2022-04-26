package com.cbd.finance.model.entity;

import com.cbd.finance.model.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "fund_transfer")
public class FundTransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionReference;
    private String fromAccount;
    private String toAccount;
    private String currency;
    private BigDecimal amount;
    private String creditDescription;
    private String debitDescription;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

}
