package com.taxdown.managementtool.domain.model;

public record Customer(String id, String firstName, String lastName, String username, String password, String email, Double availableCredit) {

    public Customer(String id, String firstName, String lastName, String username, String password, String email){
        this(id, firstName, lastName, username, password, email, 0.0);
    }
    
    public Customer(String id, String firtName){
        //For testing
        this(id, firtName, "", "", "", "", 0.0);
    }
}
