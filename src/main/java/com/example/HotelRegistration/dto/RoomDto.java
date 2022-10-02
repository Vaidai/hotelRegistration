package com.example.HotelRegistration.dto;

public class RoomDto {

    private Long id;
    private int roomNumber;
    private boolean isEmpty;

    public RoomDto() {
    }

    public RoomDto(Long id, int roomNumber, boolean isEmpty) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.isEmpty = isEmpty;
    }

    public RoomDto(int roomNumber, boolean isEmpty) {
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
        isEmpty = empty;
    }
}
