package com.taxdown.managementtool.domain.port.in;

import com.taxdown.managementtool.domain.model.Customer;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerUseCase {

    Mono<Customer> createCustomer(Customer customer);

    Mono<Customer> getCustomerById(String id);

    Flux<Customer> getAllCustomers();

    Mono<Void> deleteCustomer(String id);

    Mono<Customer> patchCustomer(Customer customer);

    Mono<Customer> addAvailableCredit(String id, Double availableCredit);

    Flux<Customer> getAllCustomersSortedByAvailableCredit();
}
