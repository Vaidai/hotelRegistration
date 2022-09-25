package com.example.HotelRegistration.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Registration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Guest guest;
    private int roomNumber;
    private boolean active;

    public Registration() {
    }

    public Registration(Guest guest, int roomNumber, boolean active) {
        this.guest = guest;
        this.roomNumber = roomNumber;
        this.active = active;
    }

    public Registration(Long id, Guest guest, int roomNumber, boolean active) {
        this.id = id;
        this.guest = guest;
        this.roomNumber = roomNumber;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
