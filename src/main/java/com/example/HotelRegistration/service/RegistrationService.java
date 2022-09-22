package com.example.HotelRegistration.service;

import com.example.HotelRegistration.model.Guest;
import com.example.HotelRegistration.model.Registration;
import com.example.HotelRegistration.model.Room;
import com.example.HotelRegistration.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    private static final String NO_EMPTY_ROOMS_MESSAGE = "There is no empty rooms";
    private static final String NOT_EXIST_REGISTRATION_MESSAGE = "There is no active registration in room number: {0}";
    private static final String SUCCESSFUL_REGISTRATION_MESSAGE = "Guest {0} {1} is register in room number: {2}.";
    private static final String SUCCESSFUL_CHECK_OUT_MESSAGE = "The guest {0} {1} has been checked out from room number: {2}.";

    private final RegistrationRepository registrationRepository;
    private final RoomService roomService;

    public RegistrationService(RegistrationRepository registrationRepository, RoomService roomService) {
        this.registrationRepository = registrationRepository;
        this.roomService = roomService;
    }

    @Transactional
    public String registerAGuest(Guest guest) {
        Room emptyRoom = roomService.getEmptyRoom();
        if (emptyRoom == null) {
            return NO_EMPTY_ROOMS_MESSAGE;
        }
        Registration registration = registrationRepository.save(new Registration(guest, emptyRoom.getRoomNumber(), true));
        emptyRoom.setStatus(true);
        return MessageFormat.format(SUCCESSFUL_REGISTRATION_MESSAGE, registration.getGuest().getFirstName(), registration.getGuest().getLastName(), emptyRoom.getRoomNumber());
    }

    @Transactional
    public String checkOutAGuest(int roomNumber) {
        Optional<Registration> registration = registrationRepository.findByRoomNumberAndActiveTrue(roomNumber);
        if (registration.isEmpty() || registration.get().isActive() == false) {
            return MessageFormat.format(NOT_EXIST_REGISTRATION_MESSAGE, roomNumber);
        }

        registration.get().setActive(false);
        String checkOutRoomNumber = roomService.changeRoomStatus(registration.get().getRoomNumber());

        return MessageFormat.format(SUCCESSFUL_CHECK_OUT_MESSAGE, registration.get().getGuest().getFirstName(), registration.get().getGuest().getLastName(), roomNumber);
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public List<Registration> showOccupiedRooms() {
        return registrationRepository.findAllByActiveTrue();
    }

    public List<Registration> showRoomHistory(int roomNumber) {
        return registrationRepository.findByRoomNumber(roomNumber);
    }
}
