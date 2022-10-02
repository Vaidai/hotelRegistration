package com.example.HotelRegistration.repository;

import com.example.HotelRegistration.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Optional<Registration> findByRoomIdAndActiveTrue(Long roomId);

    List<Registration> findAllByActiveTrue();

    Optional<List<Registration>> findByRoomId(Long roomId);

}
