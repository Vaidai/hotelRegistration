package com.example.HotelRegistration.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    private boolean status;

    public Room() {
    }

    public Room(int roomNumber, boolean status) {
        this.roomNumber = roomNumber;
        this.status = status;
    }

    public Room(Long id, int roomNumber, boolean status) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
