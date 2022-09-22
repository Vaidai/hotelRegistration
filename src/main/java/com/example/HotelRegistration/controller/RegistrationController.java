package com.example.HotelRegistration.controller;

import com.example.HotelRegistration.model.Guest;
import com.example.HotelRegistration.model.Registration;
import com.example.HotelRegistration.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Registration>> getAllRegistrations() {
        return new ResponseEntity<>(registrationService.getAllRegistrations(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAGuest(@RequestBody Guest guest) {
        return new ResponseEntity<>(registrationService.registerAGuest(guest), HttpStatus.OK);
    }

    @PutMapping("/checkOut/{roomNumber}")
    public ResponseEntity<String> checkOutAGuest(@PathVariable int roomNumber) {
        return new ResponseEntity<>(registrationService.checkOutAGuest(roomNumber), HttpStatus.OK);
    }

    @GetMapping("/showOccupiedRooms")
    public ResponseEntity<List<Registration>> showOccupiedRooms() {
        return new ResponseEntity<>(registrationService.showOccupiedRooms(), HttpStatus.OK);
    }

    @GetMapping("/showRoomHistory/{roomNumber}")
    public ResponseEntity<List<Registration>> showRoomHistory(@PathVariable int roomNumber) {
        return new ResponseEntity<>(registrationService.showRoomHistory(roomNumber), HttpStatus.OK);
    }
}
