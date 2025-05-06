package com.taxdown.managementtool.application.service;

import com.taxdown.managementtool.domain.model.Customer;
import com.taxdown.managementtool.domain.port.in.CustomerUseCase;
import com.taxdown.managementtool.domain.port.out.CustomerRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
public class CustomerService implements CustomerUseCase {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Mono<Customer> createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Void> deleteCustomer(String id) {
        return customerRepository.deleteById(id);
    }

    @Override
    public Mono<Customer> patchCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> addAvailableCredit(String id, Double availableCredit) {
       Mono<Customer> databaseDocument = this.customerRepository.findById(id);
        return customerRepository.save(databaseDocument.map( customer ->
                new Customer(customer.id(), customer.firstName(), customer.lastName(), customer.username(), customer.password(), customer.email(), availableCredit)
        ).block());        
    }

    @Override
    public Flux<Customer> getAllCustomersSortedByAvailableCredit() {
        return customerRepository.findAll(Sort.by(Sort.Order.asc("availableCredit")))
                .sort(Comparator.comparing(Customer::availableCredit).reversed());//Sorted two times because Flux can return memory data
    }
}
