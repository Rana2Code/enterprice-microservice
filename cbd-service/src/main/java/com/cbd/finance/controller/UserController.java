package com.cbd.finance.controller;

import com.cbd.finance.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/cbd/user")
@RequiredArgsConstructor
public class UserController {

	@Autowired  
	UserService userService;
	
	@Autowired  
	MessageSource messageSource;

    @GetMapping(value = "/{identification}")
    public ResponseEntity readUser(@PathVariable("identification") String identification) {
        return ResponseEntity.ok(userService.readUser(identification));
    }

    @GetMapping
    public ResponseEntity readUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.readUsers(pageable));
    }

}
