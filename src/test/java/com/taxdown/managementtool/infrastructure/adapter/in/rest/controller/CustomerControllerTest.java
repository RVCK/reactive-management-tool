package com.taxdown.managementtool.infrastructure.adapter.in.rest.controller;

import com.taxdown.managementtool.application.service.CustomerService;
import com.taxdown.managementtool.domain.model.Customer;
import com.taxdown.managementtool.domain.port.in.CustomerUseCase;
import com.taxdown.managementtool.infrastructure.adapter.in.rest.CustomerController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@WebFluxTest(CustomerController.class)
@Import(CustomerService.class)
public class CustomerControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private CustomerUseCase customerUseCase;

    private final Customer sampleCustomer = new Customer("1", "John", "Doe", "jdoe", "pass", "john@example.com", 100.0);

    @Test
    void getCustomerById_ReturnsCustomer_WhenExists() {
        Mockito.when(customerUseCase.getCustomerById("1")).thenReturn(Mono.just(sampleCustomer));

        webTestClient.get()
                .uri("/customers/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.firstName").isEqualTo("John");
    }

    @Test
    void getCustomerById_Returns404_WhenNotFound() {
        Mockito.when(customerUseCase.getCustomerById("none")).thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/customers/none")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void createCustomer_ReturnsCustomer_WhenSuccessful() {
        Mockito.when(customerUseCase.createCustomer(any(Customer.class))).thenReturn(Mono.just(sampleCustomer));

        webTestClient.post()
                .uri("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sampleCustomer)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.firstName").isEqualTo("John");
    }

    @Test
    void getAllCustomers_ReturnsListOfCustomers() {
        List<Customer> customers = List.of(sampleCustomer);
        Mockito.when(customerUseCase.getAllCustomers()).thenReturn(Flux.fromIterable(customers));

        webTestClient.get()
                .uri("/customers")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Customer.class).hasSize(1);
    }

    @Test
    void deleteCustomer_Returns200_WhenSuccessful() {
        Mockito.when(customerUseCase.deleteCustomer("1")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/customers/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void patchCustomer_ReturnsUpdatedCustomer_WhenSuccessful() {
        Mockito.when(customerUseCase.patchCustomer(any(Customer.class)))
                .thenReturn(Mono.just(sampleCustomer));

        webTestClient.patch()
                .uri("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sampleCustomer)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.firstName").isEqualTo("John");
    }

    @Test
    void addAvailableCredit_ReturnsUpdatedCustomer_WhenSuccessful() {
        Mockito.when(customerUseCase.addAvailableCredit("1", 200.0))
                .thenReturn(Mono.just(new Customer("1", "John", "Doe", "jdoe", "pass", "john@example.com", 200.0)));

        webTestClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/customers/1")
                        .queryParam("availableCredit", 200.0)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.availableCredit").isEqualTo(200.0);
    }

    @Test
    void getAllCustomersSortedByAvailableCredit_ReturnsSortedList() {
        Customer customer1 = new Customer("1", "John", "Doe", "jdoe", "pass", "john@example.com", 100.0);
        Customer customer2 = new Customer("2", "Jane", "Smith", "jsmith", "pass", "jane@example.com", 200.0);

        Mockito.when(customerUseCase.getAllCustomersSortedByAvailableCredit())
                .thenReturn(Flux.just(customer2, customer1)); // sorted descending

        webTestClient.get()
                .uri("/customers/credit")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Customer.class)
                .value(customers -> {
                    assertThat(customers).hasSize(2);
                    assertThat(customers.get(0).availableCredit()).isEqualTo(200.0);
                    assertThat(customers.get(1).availableCredit()).isEqualTo(100.0);
                });
    }

}