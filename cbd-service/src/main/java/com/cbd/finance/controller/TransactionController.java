package com.cbd.finance.controller;

import com.cbd.finance.model.dto.request.FundTransferRequest;
import com.cbd.finance.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cbd/transaction")
public class TransactionController {

    @Autowired 
    TransactionService transactionService;

    @PostMapping("/fund-transfer")
    public ResponseEntity fundTransfer(@RequestBody FundTransferRequest fundTransferRequest) {

//        log.info("Account to account transfer initiated in core bank from {}", fundTransferRequest.toString());
        return ResponseEntity.ok(transactionService.fundTransfer(fundTransferRequest));

    }


}
