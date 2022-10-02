package com.example.HotelRegistration.service;

import com.example.HotelRegistration.model.Guest;
import com.example.HotelRegistration.model.Registration;
import com.example.HotelRegistration.model.Room;
import com.example.HotelRegistration.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RoomService roomService;

    public RegistrationService(RegistrationRepository registrationRepository, RoomService roomService) {
        this.registrationRepository = registrationRepository;
        this.roomService = roomService;
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Transactional
    public Registration registerAGuest(Guest guest) {
        Optional<Room> emptyRoom = roomService.getEmptyRoom();
        if (emptyRoom.isEmpty()) {
            return null;
        }

        Registration registration = registrationRepository.save(new Registration(guest, emptyRoom.get().getId(), true));
        emptyRoom.get().setEmpty(false);
        return registration;
    }


    @Transactional
    public Registration checkOutAGuest(Long roomId) {
        Optional<Registration> registration = registrationRepository.findByRoomIdAndActiveTrue(roomId);
        if (registration.isEmpty() || registration.get().isActive() == false) {
            return null;
        }

        String checkoutRoomNumber = roomService.checkOutRoom(registration.get().getRoomId());
        if (checkoutRoomNumber == null) {
            return null;
        }

        registration.get().setActive(false);
        return registration.get();
    }

    public List<Registration> showCheckedInRooms() {
        return registrationRepository.findAllByActiveTrue().get();
    }


    public List<Registration> showRoomHistory(Long roomId) {
        Optional<List<Registration>> roomRegistrations = registrationRepository.findByRoomId(roomId);
        if (!roomRegistrations.isPresent()){
            return null;
        }
        return roomRegistrations.get();
    }
}
