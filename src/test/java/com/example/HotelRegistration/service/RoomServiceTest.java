package com.example.HotelRegistration.service;

import com.example.HotelRegistration.model.Room;
import com.example.HotelRegistration.repository.RoomRepository;
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
class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;
    private RoomService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RoomService(roomRepository);
    }

    @Test
    void canGetAllRooms() {
        underTest.getAllRooms();
        verify(roomRepository).findAll();
    }

    @Test
    void canAddRoom() {
        Room room = new Room(1, true);
        underTest.addRoom(room);
        ArgumentCaptor<Room> roomArgumentCaptor = ArgumentCaptor.forClass(Room.class);
        verify(roomRepository).save(roomArgumentCaptor.capture());
        Room capturedRoom = roomArgumentCaptor.getValue();
        assertThat(capturedRoom).isEqualTo(room);
    }

    @Test
    void getEmptyRoom() {
        underTest.getEmptyRoom();
        verify(roomRepository).findFirstByIsEmptyTrue();
    }

    @Test
    void checkOutRoom_withNotExistingRegistration_returnNull() {
        Long roomId = 1l;
        underTest.checkOutRoom(roomId);
        ArgumentCaptor<Room> roomArgumentCaptor = ArgumentCaptor.forClass(Room.class);

        verify(roomRepository).findById(roomId);
        Room capturedRoom = roomArgumentCaptor.getValue();

        assertThat(capturedRoom).isEqualTo(null);
    }

    @Test
    void checkOutRoom_withExistingRegistration_returnRoomNumber() {
        Long roomId = 1l;
        underTest.checkOutRoom(roomId);
        ArgumentCaptor<Room> roomArgumentCaptor = ArgumentCaptor.forClass(Room.class);

        verify(roomRepository).findById(roomId);
        Room capturedRoom = roomArgumentCaptor.getValue();

        assertThat(capturedRoom).isEqualTo(1);
    }

}