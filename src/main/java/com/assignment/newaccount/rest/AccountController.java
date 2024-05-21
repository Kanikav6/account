package com.assignment.newaccount.rest;

import com.assignment.newaccount.entities.Account;
import com.assignment.newaccount.entities.Customer;
import com.assignment.newaccount.service.AccountService;
import com.assignment.newaccount.service.CustomerService;
import com.assignment.newaccount.vo.NewAccountRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;



    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody NewAccountRequest request) {
        log.info("New Account creation request received: " + request);
        return ResponseEntity.ok(accountService.createNewAccount(request.getCustomerId(), request.getInitialCredit()));
    }


    @GetMapping("/customer/{id}")
    public Customer getCustomerDetails(@PathVariable Long id) {
        log.info("Received request to get customer details for id: {}", id);
        return customerService.getCustomerDetails(id);
    }


}
