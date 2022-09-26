package com.example.HotelRegistration.service;

import com.example.HotelRegistration.model.Guest;
import com.example.HotelRegistration.model.Registration;
import com.example.HotelRegistration.repository.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private RegistrationRepository registrationRepository;
    private RegistrationService underTest;
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        underTest = new RegistrationService(registrationRepository, roomService);
    }

    @Test
    void canGetAllRegistrations() {
        underTest.getAllRegistrations();
        verify(registrationRepository).findAll();
    }

    @Test
    void canRegisterAGuest() {
        Guest guest = new Guest("TestMame", "TestLastName");

        underTest.registerAGuest(guest);

        ArgumentCaptor<Registration> registrationArgumentCaptor = ArgumentCaptor.forClass(Registration.class);
        verify(registrationRepository).save(registrationArgumentCaptor.capture());
        Registration capturedRegistration = registrationArgumentCaptor.getValue();
        assertThat(capturedRegistration).isEqualTo(guest);
    }

    @Test
    void checkOutAGuest() {
    }

    @Test
    void showCheckedInRooms() {

    }

    @Test
    void showRoomHistory() {
        Long roomId = 1l;
        underTest.showRoomHistory(roomId);
        verify(registrationRepository).findByRoomId(roomId);
    }

}