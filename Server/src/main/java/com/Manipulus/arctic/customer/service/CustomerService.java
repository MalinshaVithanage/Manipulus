package com.Manipulus.arctic.customer.service;
import com.Manipulus.arctic.customer.exception.CustomerNotFoundException;
import com.Manipulus.arctic.customer.model.Customer;
import com.Manipulus.arctic.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private final com.Manipulus.arctic.customer.repository.CustomerRepository CustomerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.CustomerRepository= customerRepository;
    }

    // Add a new customer
    public Customer addCustomer(Customer customer){
        // Generate a unique customer code
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCustomerCode(UUID.randomUUID().toString());
        customer.setActive_status(true);
        // Save the new customer to the database
        return CustomerRepository.save(customer);
    }
    // Find all customers
    public List<Customer> findAllCustomers(){
    // Retrieve all customers from the database
        return CustomerRepository.findAll();
    }
    // Update an existing customer
    public Customer updateCustomer(Customer customer){
        // Save the updated customer to the database
        return CustomerRepository.save(customer);
    }
    // Find a customer by their ID
    public Customer findCustomerById(Long id){
        // Find the customer with the given ID in the database
        // Throw a CustomerNotFoundException if the customer is not found
        return CustomerRepository.findCustomerById(id)
                .orElseThrow(()-> new CustomerNotFoundException(" customer by id"+ id + "was not found"));
    }
    // Delete a customer by their ID
    @Transactional
    public void deleteCustomerById(Long id){
        CustomerRepository.deleteCustomerById(id);
    }
    }

