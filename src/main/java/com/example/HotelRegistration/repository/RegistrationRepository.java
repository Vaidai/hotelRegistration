package com.example.HotelRegistration.repository;

import com.example.HotelRegistration.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Optional<Registration> findByRoomNumberAndActiveTrue(int roomNumber);
    List<Registration> findAllByActiveTrue();
    List<Registration> findByRoomNumber(int roomNumber);
}