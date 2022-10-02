package com.example.HotelRegistration.controller;

import com.example.HotelRegistration.dto.RoomDto;
import com.example.HotelRegistration.model.Room;
import com.example.HotelRegistration.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService service;
    private ModelMapper modelMapper;

    public RoomController(RoomService service) {
        this.service = service;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        List<Room> rooms = service.getAllRooms();
        List<RoomDto> roomsDto = rooms
                .stream()
                .map(source -> modelMapper.map(source, RoomDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(roomsDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<RoomDto> addRoom(@RequestBody Room room) {
        Room addedRoom = service.addRoom(room);
        RoomDto roomDto = modelMapper.map(addedRoom, RoomDto.class);
        return new ResponseEntity<>(roomDto, HttpStatus.CREATED);
    }
}
