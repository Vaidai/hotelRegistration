package com.example.HotelRegistration.dto;

import com.example.HotelRegistration.model.Guest;

public class RegistrationDto {

    private Long id;
    private Guest guest;
    private Long roomId;
    private boolean active;

    public RegistrationDto() {
    }

    public RegistrationDto(Guest guest, Long roomId, boolean active) {
        this.guest = guest;
        this.roomId = roomId;
        this.active = active;
    }

    public RegistrationDto(Long id, Guest guest, Long roomId, boolean active) {
        this.id = id;
        this.guest = guest;
        this.roomId = roomId;
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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
