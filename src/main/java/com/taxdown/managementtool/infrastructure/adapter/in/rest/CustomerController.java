package com.taxdown.managementtool.infrastructure.adapter.in.rest;

import com.taxdown.managementtool.application.service.CustomerService;
import com.taxdown.managementtool.domain.model.Customer;
import com.taxdown.managementtool.domain.port.in.CustomerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    public CustomerController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    //CRUD (Create, Read, Update, Delete) endpoints:
    @PostMapping
    public Mono<Customer> createCustomer(@RequestBody Customer customer) {
        return customerUseCase.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public Mono<Customer> getCustomerById(@PathVariable String id) {
        return customerUseCase.getCustomerById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")));
    }

    @GetMapping
    public Flux<Customer> getAllCustomers() {
        return customerUseCase.getAllCustomers();
    }
    
    @PatchMapping
    public Mono<Customer> patchCustomer(@RequestBody Customer customer){
        return customerUseCase.patchCustomer(customer)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id) {
        return customerUseCase.deleteCustomer(id);
    }
    
    //Custom endpoints:
    @PutMapping("/{id}")
    public Mono<Customer> addAvailableCredit(@PathVariable String id, @RequestParam Double availableCredit){
        return customerUseCase.addAvailableCredit(id, availableCredit)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found")));
    }

    @GetMapping("/credit")
    public Flux<Customer> getAllCustomersSortedByAvailableCredit() {
        return customerUseCase.getAllCustomersSortedByAvailableCredit();
    }
}
