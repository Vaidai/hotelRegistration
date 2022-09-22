package com.example.HotelRegistration.model;

import java.io.Serializable;

public class Guest implements Serializable {
    private String firstName;
    private String lastName;

    public Guest() {
    }

    public Guest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
