package com.assignment.newaccount.rest;

import com.assignment.newaccount.entities.Account;
import com.assignment.newaccount.entities.Customer;
import com.assignment.newaccount.repository.CustomerRepo;
import com.assignment.newaccount.service.AccountService;
import com.assignment.newaccount.service.CustomerService;
import com.assignment.newaccount.vo.NewAccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest {

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private AccountService accountService;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void testCreateAccountWithInitialCredit() throws Exception {

        Account mockAccount = new Account();
        mockAccount.setAccountId(1L);
        mockAccount.setAccountBalance(0.0);
        when(accountService.createNewAccount(any(Long.class), any(Double.class))).thenReturn(mockAccount);

        String requestJson = new ObjectMapper()
                .writeValueAsString(new NewAccountRequest(100L, 0.0));

        mockMvc.perform(post("/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(mockAccount.getAccountId()))
                .andExpect(jsonPath("$.accountBalance").value(mockAccount.getAccountBalance()));
    }

    @Test
    void testGetCustomerDetails() throws Exception {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("Vaishali");
        customer.setLastName("Aggarwal");

        when(customerService.getCustomerDetails(customerId)).thenReturn(customer);

        mockMvc.perform(get("/accounts/customer/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId))
                .andExpect(jsonPath("$.firstName").value("Vaishali"))
                .andExpect(jsonPath("$.lastName").value("Aggarwal"));
    }
}

