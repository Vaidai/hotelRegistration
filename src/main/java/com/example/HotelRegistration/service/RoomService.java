package com.example.HotelRegistration.service;

import com.example.HotelRegistration.model.Room;
import com.example.HotelRegistration.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> getAllRooms() {
        return repository.findAll();
    }

    public Room addRoom(Room room) {
        return repository.save(room);
    }

    public Room getEmptyRoom() {
        return repository.findFirstByStatusFalse();
    }

    public String changeRoomStatus(int roomNumber) {
        Room room = repository.findByRoomNumber(roomNumber);
        if(room == null){
            return null;
        }
        room.setStatus(false);
        return String.valueOf(room.getRoomNumber());
    }
}
