package com.example.HotelRegistration.controller;

import com.example.HotelRegistration.model.Room;
import com.example.HotelRegistration.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService service;
    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Room>> getAllRooms() {
        return new ResponseEntity<>(service.getAllRooms(), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Room> updateRooms(@RequestBody Room room) {
        Room updatedRoom = service.updateRoom(room);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        return new ResponseEntity<>(service.addRoom(room), HttpStatus.CREATED);
    }
}
