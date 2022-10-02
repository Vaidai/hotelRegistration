package com.example.HotelRegistration.service;

import com.example.HotelRegistration.model.Room;
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
import static org.mockito.ArgumentMatchers.any;

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
    void whetGetAllRooms_returnAllRooms() {
        List<Room> expected = Arrays.asList(
                new Room(1, true),
                new Room(2, true),
                new Room(3, true)
        );
        Mockito.when(roomRepository.findAll()).thenReturn(expected);

        List<Room> rooms = underTest.getAllRooms();

        Assertions.assertEquals(expected, rooms);
    }

    @Test
    void whenGetEmptyRoom_returnEmptyRoom() {
        Optional<Room> expected = Optional.of(new Room(1, true));
        Mockito.when(roomRepository.findFirstByIsEmptyTrue()).thenReturn(expected);

        Optional<Room> room = underTest.getEmptyRoom();

        Assertions.assertEquals(expected, room);
    }

    @Test
    void whenGetEmptyRoom_returnNull() {
        Mockito.when(roomRepository.findFirstByIsEmptyTrue()).thenReturn(Optional.empty());

        Optional<Room> room = underTest.getEmptyRoom();

        Assertions.assertEquals(Optional.empty(), room);
    }

    @Test
    void whenCheckOutRoom_thenReturnRoomNumber() {
        Optional<Room> expected = Optional.of(new Room(1, false));
        Mockito.when(roomRepository.findByIdAndIsEmptyFalse(1l)).thenReturn(expected);

        String result = underTest.checkOutRoom(1l);

        Assertions.assertEquals(String.valueOf(expected.get().getRoomNumber()), result);
    }

    @Test
    void whenCheckOutRoom_thenReturnNull() {
        Mockito.when(roomRepository.findByIdAndIsEmptyFalse(1l)).thenReturn(Optional.empty());

        String result = underTest.checkOutRoom(1l);

        Assertions.assertEquals(null, result);
    }

    @Test
    void whenAddRoom_returnOk() {
        Room expected = new Room(1, true);
        Mockito.when(roomRepository.save(any())).thenReturn(expected);

        Room result = underTest.addRoom(expected);

        Assertions.assertEquals(expected, result);
    }
}