package com.example.HotelRegistration.service;

import com.example.HotelRegistration.model.Guest;
import com.example.HotelRegistration.model.Registration;
import com.example.HotelRegistration.model.Room;
import com.example.HotelRegistration.repository.RegistrationRepository;
import com.example.HotelRegistration.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    private RegistrationService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RegistrationService(registrationRepository, roomService);
    }


    @Test
    void whenGetAllRegistrations_returnAList() {
        List<Registration> expected = Arrays.asList(
                new Registration(new Guest("First", "Last"), 1l, false),
                new Registration(new Guest("Second", "Last"), 2l, true),
                new Registration(new Guest("Third", "Last"), 3l, true),
                new Registration(new Guest("First Again", "Last"), 1l, true)
        );
        Mockito.when(registrationRepository.findAll()).thenReturn(expected);

        List<Registration> result = underTest.getAllRegistrations();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void whenRegisterAGuest_returnError() {
        Mockito.when(roomService.getEmptyRoom()).thenReturn(Optional.empty());

        Registration result = underTest.registerAGuest(new Guest("d", "f"));

        Assertions.assertEquals(null, result);
    }

    @Test
    void whenRegisterAGuest_returnOk() {
        Guest guest = new Guest("First", "Last");
        Optional<Room> emptyRoom = Optional.of(new Room(1l, 1, true));
        Registration expected = new Registration(1l, guest, emptyRoom.get().getId(), true);

        Mockito.when(registrationRepository.save(new Registration(guest, emptyRoom.get().getId(), true))).thenReturn(expected);

        Registration result = underTest.registerAGuest(guest);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void whenCheckOutAGuest_withNotExistRegistration_returnError() {
        long roomId = 1L;
        Mockito.when(registrationRepository.findByRoomIdAndActiveTrue(roomId)).thenReturn(Optional.empty());

        Registration result = underTest.checkOutAGuest(roomId);

        Assertions.assertEquals(null, result);
    }

    @Test
    void whenCheckOutAGuest_withExistingRegistration_returnOk() {
        long roomId = 1L;
        Optional<Registration> expected = Optional.of(new Registration(roomId, new Guest("Second", "Last"), roomId, true));
        Mockito.when(registrationRepository.findByRoomIdAndActiveTrue(roomId)).thenReturn(expected);

        Registration result = underTest.checkOutAGuest(roomId);

        Assertions.assertEquals(expected.get(), result);
    }

    @Test
    void whenShowCheckedInRooms_returnListOfRooms() {
        List<Registration> expected = Arrays.asList(
                new Registration(new Guest("Second", "Last"), 2l, true),
                new Registration(new Guest("Third", "Last"), 3l, true),
                new Registration(new Guest("First Again", "Last"), 1l, true)
        );
        Mockito.when(registrationRepository.findAllByActiveTrue()).thenReturn(expected);

        List<Registration> result = underTest.showCheckedInRooms();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void whenShowRoomHistory_returnRoomHistory() {
        List<Registration> expected = Arrays.asList(
                new Registration(new Guest("First", "Last"), 1l, false),
                new Registration(new Guest("First Again", "Last"), 1l, true)
        );
        Mockito.when(registrationRepository.findByRoomId(1L)).thenReturn(Optional.of(expected));

        List<Registration> result = underTest.showRoomHistory(1l);

        Assertions.assertEquals(expected, result);
    }
}