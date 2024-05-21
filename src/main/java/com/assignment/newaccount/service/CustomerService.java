package com.assignment.newaccount.service;

import com.assignment.newaccount.entities.Customer;
import com.assignment.newaccount.repository.CustomerRepo;
import com.assignment.newaccount.rest.AccountController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    public CustomerRepo customerRepo;

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    public Customer getCustomerDetails(Long customerId){
        log.info("Fetching customer details for customerId: {} " , customerId);
        return customerRepo.findById(customerId)
                .orElseThrow(() -> {
                    log.error("Customer not found with id: {}", customerId);
                    return new RuntimeException("Customer not found");
                });
    }


}
