package com.taxdown.managementtool.infrastructure.adapter.out.mongo;

import com.taxdown.managementtool.domain.model.Customer;
import com.taxdown.managementtool.domain.port.out.CustomerRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerMongoRepository extends ReactiveMongoRepository<Customer, String>, CustomerRepository {
    
    @Override
    Mono<Customer> save(Customer customer);

    @Override
    Mono<Customer> findById(String id);

    @Override
    Flux<Customer> findAll();

    @Override
    Mono<Void> deleteById(String id);

    @Override
    Flux<Customer> findAll(Sort sort);
    
}
