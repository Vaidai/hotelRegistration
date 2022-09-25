package com.example.HotelRegistration.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    private boolean isEmpty;

    public Room() {
    }

    public Room(int roomNumber, boolean isEmpty) {
        this.roomNumber = roomNumber;
        this.isEmpty = isEmpty;
    }

    public Room(Long id, int roomNumber, boolean isEmpty) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.isEmpty = isEmpty;
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

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        this.isEmpty = empty;
    }
}
