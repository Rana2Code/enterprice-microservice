package com.cbd.finance.service.rest.client;

import com.cbd.finance.configuration.CustomFeignClientConfiguration;
import com.cbd.finance.model.dto.request.FundTransferRequest;
import com.cbd.finance.model.dto.response.AccountResponse;
import com.cbd.finance.model.dto.response.FundTransferResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cbd-service", configuration = CustomFeignClientConfiguration.class)
public interface BankingCoreFeignClient {

    @RequestMapping(path = "/cbd/account/bank-account/{account_number}", method = RequestMethod.GET)
    AccountResponse readAccount(@PathVariable("account_number") String accountNumber);

    @RequestMapping(path = "/cbd/transaction/fund-transfer", method = RequestMethod.POST)
    FundTransferResponse fundTransfer(@RequestBody FundTransferRequest fundTransferRequest);

}
