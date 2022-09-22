package com.example.HotelRegistration.repository;

import com.example.HotelRegistration.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findFirstByStatusFalse();
    Room findByRoomNumber(int roomNumber);
}
