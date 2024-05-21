package com.assignment.newaccount.service;

import com.assignment.newaccount.entities.Account;
import com.assignment.newaccount.entities.Customer;
import com.assignment.newaccount.entities.Transaction;
import com.assignment.newaccount.repository.AccountRepo;
import com.assignment.newaccount.repository.CustomerRepo;
import com.assignment.newaccount.repository.TransactionRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    @Mock
    private AccountRepo accountRepository;

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private TransactionRepo transactionRepo;


    @Mock
    private CustomerService customerService;

    @InjectMocks
    private AccountService accountService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNewAccountWithAmount() {
        Long customerId = 100L;
        double initialCredit = 100.0;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerRepo.findById(customerId)).thenReturn(Optional.of(customer));
        Account account = new Account();
        account.setCustomer(customer);
        account.setAccountBalance(initialCredit);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createNewAccount(customerId, initialCredit);

        assertNotNull(createdAccount);
        assertEquals(initialCredit, createdAccount.getAccountBalance());
        verify(transactionRepo, times(1)).save(any(Transaction.class));
    }

    @Test
    void testCreateAccountWithoutAmount() {
        Long customerId = 100L;
        double initialCredit = 0.0;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerRepo.findById(customerId)).thenReturn(Optional.of(customer));

        Account account = new Account();
        account.setCustomer(customer);
        account.setAccountBalance(initialCredit);

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createNewAccount(customerId, initialCredit);
        assertNotNull(createdAccount);
        assertEquals(initialCredit, createdAccount.getAccountBalance());

        verify(transactionRepo, never()).save(any(Transaction.class));
    }


}
