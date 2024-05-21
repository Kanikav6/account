package com.assignment.newaccount.service;

import com.assignment.newaccount.entities.Customer;
import com.assignment.newaccount.repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Mock
    private CustomerRepo customerRepo;

    @InjectMocks
    private CustomerService customerService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetExistingCustomerDetails() {
        Long customerId = 100L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName("Vaishali");
        customer.setLastName("Aggarwal");

        when(customerRepo.findById(customerId)).thenReturn(Optional.of(customer));

        Customer fetchedCustomer = customerService.getCustomerDetails(customerId);

        assertNotNull(fetchedCustomer);
        assertEquals("Vaishali", fetchedCustomer.getFirstName());
        assertEquals("Aggarwal", fetchedCustomer.getLastName());
    }

    @Test
    void testGetNonExistingCustomerDetails() {
        Long customerId = 2L;

        when(customerRepo.findById(customerId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerService.getCustomerDetails(customerId);
        });
        assertEquals("Customer not found", exception.getMessage());
    }
}
