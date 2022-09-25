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

    @PutMapping("/checkOut/{roomId}")
    public ResponseEntity<String> checkOutAGuest(@PathVariable Long roomId) {
        return new ResponseEntity<>(registrationService.checkOutAGuest(roomId), HttpStatus.OK);
    }

    @GetMapping("/showCheckedInRooms")
    public ResponseEntity<List<Registration>> showCheckedInRooms() {
        return new ResponseEntity<>(registrationService.showCheckedInRooms(), HttpStatus.OK);
    }

    @GetMapping("/showRoomHistory/{roomId}")
    public ResponseEntity<List<Registration>> showRoomHistory(@PathVariable Long roomId) {
        return new ResponseEntity<>(registrationService.showRoomHistory(roomId), HttpStatus.OK);
    }
}
