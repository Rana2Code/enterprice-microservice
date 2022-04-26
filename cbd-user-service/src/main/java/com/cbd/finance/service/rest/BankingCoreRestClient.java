package com.cbd.finance.service.rest;

import com.cbd.finance.configuration.CustomFeignClientConfiguration;
import com.cbd.finance.model.rest.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cbd-service", configuration = CustomFeignClientConfiguration.class)
public interface BankingCoreRestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/cbd/user/{identification}")
    UserResponse readUser(@PathVariable("identification") String identification);

}
