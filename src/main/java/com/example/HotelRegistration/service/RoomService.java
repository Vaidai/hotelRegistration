package com.example.HotelRegistration.service;

import com.example.HotelRegistration.model.Room;
import com.example.HotelRegistration.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Room addRoom(Room room) {
        return repository.save(room);
    }

    public Optional<Room> getEmptyRoom() {
        return repository.findFirstByIsEmptyTrue();
    }

    @Transactional
    public String checkOutRoom(Long roomId) {
        Optional<Room> room = repository.findByIdAndIsEmptyFalse(roomId);
        if (room.isEmpty()) {
            return null;
        }
        room.get().setEmpty(true);
        return String.valueOf(room.get().getRoomNumber());
    }
}
