package com.example.HotelRegistration.repository;

import com.example.HotelRegistration.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findFirstByIsEmptyTrue();

    Optional<Room> findByIdAndIsEmptyFalse(Long roomId);
}
