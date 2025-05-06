package com.taxdown.managementtool.application.service;

import com.taxdown.managementtool.domain.model.Customer;
import com.taxdown.managementtool.domain.port.out.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;
    
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("1", "John Doe");
    }

    @Test
    void createCustomer(){
        //Ini
        Mono<Customer> monoCustomer = Mono.just(this.customer);
        when(this.customerRepository.save(any())).thenReturn(monoCustomer);
    
        //Main
        Mono<Customer> result = this.customerService.createCustomer(this.customer);

        //Verify
        Assertions.assertNotNull(result.block());
    }

    @Test
    void readCustomerId(){        
        //Ini
        Mono<Customer> monoCustomer = Mono.just(this.customer);
        when(this.customerRepository.findById(any())).thenReturn(monoCustomer);

        //Main
        Mono<Customer> result = this.customerService.getCustomerById("1");

        //Verify
        Assertions.assertNotNull(result.block());
    }
    

    @Test
    void readAllCustomers(){
        //Ini
        Flux<Customer> fluxCustomer = Flux.just(this.customer);
        when(this.customerRepository.findAll()).thenReturn(fluxCustomer);

        //Main
        Flux<Customer> result = this.customerService.getAllCustomers();

        //Verify
        Assertions.assertFalse(result.collectList().block().isEmpty());
    }

    @Test
    void patchFullCustomerResource(){
        //Ini
        Mono<Customer> monoCustomer = Mono.just(this.customer);
        when(this.customerRepository.save(any())).thenReturn(monoCustomer);

        //Main
        Mono<Customer> result = this.customerService.patchCustomer(this.customer);

        //Verify
        Assertions.assertNotNull(result.block());
    }
    

    @Test
    void deleteCustomer(){
        //Ini
        Mono<Void> voidMono = Mono.empty();
        when(this.customerRepository.deleteById(any())).thenReturn(voidMono);

        //Main
        Mono<Void> result = this.customerService.deleteCustomer("1");

        //Verify
        Assertions.assertNull(result.block());
    }
    
    @Test
    void updateCustomerCredit(){
        //Ini
        Mono<Customer> customer = Mono.just(this.customer);
        when(this.customerRepository.findById(any())).thenReturn(customer);
        when(this.customerRepository.save(any())).thenReturn(customer);

        //Main
        Mono<Customer> result = this.customerService.addAvailableCredit("1",100.0);

        //Verify
        Assertions.assertNotNull(result.block());
    }
    

    @Test
    void readSortedByCredit(){
        //Ini
        Flux<Customer> customer = Flux.just(this.customer);
        when(this.customerRepository.findAll(any())).thenReturn(customer);

        //Main
        Flux<Customer> result = this.customerService.getAllCustomersSortedByAvailableCredit();

        //Verify
        Assertions.assertFalse(result.collectList().block().isEmpty());
    }
}
