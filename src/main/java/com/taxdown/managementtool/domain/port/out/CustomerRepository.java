package com.taxdown.managementtool.domain.port.out;

import com.taxdown.managementtool.domain.model.Customer;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository {
    Mono<Customer> save(Customer customer);
    Mono<Customer> findById(String id);
    Flux<Customer> findAll();
    Mono<Void> deleteById(String id);
    Flux<Customer> findAll(Sort sort);
}
