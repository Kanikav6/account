package com.assignment.newaccount.service;

import com.assignment.newaccount.entities.Account;
import com.assignment.newaccount.entities.Customer;
import com.assignment.newaccount.entities.Transaction;
import com.assignment.newaccount.repository.AccountRepo;
import com.assignment.newaccount.repository.TransactionRepo;
import com.assignment.newaccount.vo.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Transactional
    public Account createNewAccount(Long customerId, double initialCredit) {
        log.info("Creating new current account for customer: {} with initialCredit: {} ", customerId, initialCredit);
        Customer customer = customerService.getCustomerDetails(customerId   );

        Account account = new Account();
        account.setCustomer(customer);
        account.setAccountBalance(initialCredit);
        account.setCreatedDt(LocalDateTime.now());
        account = accountRepo.save(account);
        log.debug("Created account: {} ", account);

        if (initialCredit > 0) {
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setTransactionType(TransactionType.CREDIT);
            transaction.setTransactionDt(LocalDateTime.now());
            transaction.setTransactionAmt(initialCredit);
            transactionRepo.save(transaction);
            log.debug("Saved transaction : {}" , transaction);

        }
        return account;
    }

}
