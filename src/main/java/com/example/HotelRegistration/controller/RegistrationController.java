package com.example.HotelRegistration.controller;

import com.example.HotelRegistration.dto.RegistrationDto;
import com.example.HotelRegistration.model.Guest;
import com.example.HotelRegistration.model.Registration;
import com.example.HotelRegistration.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    private ModelMapper modelMapper;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations() {
        List<Registration> registrations = registrationService.getAllRegistrations();
        List<RegistrationDto> registrationsDto = registrations
                .stream()
                .map(source -> modelMapper.map(source, RegistrationDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(registrationsDto, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationDto> registerAGuest(@RequestBody Guest guest) {
        Registration registration = registrationService.registerAGuest(guest);
        RegistrationDto registrationDto = modelMapper.map(registration, RegistrationDto.class);
        return new ResponseEntity<>(registrationDto, HttpStatus.OK);
    }

    @PutMapping("/checkOut/{roomId}")
    public ResponseEntity<RegistrationDto> checkOutAGuest(@PathVariable Long roomId) {
        Registration registration = registrationService.checkOutAGuest(roomId);
        RegistrationDto registrationDto = modelMapper.map(registration, RegistrationDto.class);
        return new ResponseEntity<>(registrationDto, HttpStatus.OK);
    }

    @GetMapping("/showCheckedInRooms")
    public ResponseEntity<List<RegistrationDto>> showCheckedInRooms() {
        List<Registration> registrations = registrationService.showCheckedInRooms();
        List<RegistrationDto> registrationsDto = registrations
                .stream()
                .map(source -> modelMapper.map(source, RegistrationDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(registrationsDto, HttpStatus.OK);
    }

    @GetMapping("/showRoomHistory/{roomId}")
    public ResponseEntity<List<RegistrationDto>> showRoomHistory(@PathVariable Long roomId) {
        List<Registration> registrations = registrationService.showRoomHistory(roomId);
        List<RegistrationDto> registrationsDto = registrations
                .stream()
                .map(source -> modelMapper.map(source, RegistrationDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(registrationsDto, HttpStatus.OK);
    }

}
