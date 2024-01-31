package com.example.neilassignment2af.controllers;

import com.example.neilassignment2af.controllers.dtos.NewUserDTO;
import com.example.neilassignment2af.controllers.handlers.ConflictException;
import com.example.neilassignment2af.entities.MyUser;
import com.example.neilassignment2af.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // http://localhost:8080/users returns 200 ok and object
    @GetMapping({"/",""})
    List<MyUser> findAll(){
        return userRepository.findAll();
    }

    // http://localhost:8080/users returns 200 ok and object

    /*
    {
    "userEmail": "newuser1@gmail.com",
    "userPassword": "Secret123",
    "userLocked": 0,
    "userRole" : "OFFICE_STAFF",
    "userPhoneNumber" : "0876543248",
    "userPPSN" : "P765432d"
}
     */

    //manager@gmail.com, Secret123
    @PostMapping({"/",""})
    MyUser addNewUser(@Valid @RequestBody NewUserDTO newUserDTO) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        if (userRepository.existsById(newUserDTO.userEmail())) {
            throw new ConflictException("User with this email already exists");
        }
        else {
            return userRepository.save(new MyUser(newUserDTO.userEmail(), passwordEncoder.encode(newUserDTO.userPassword()), newUserDTO.userLocked(), newUserDTO.userRole(), newUserDTO.userPhoneNumber(), newUserDTO.userPPSN()));
        }
    }
}
