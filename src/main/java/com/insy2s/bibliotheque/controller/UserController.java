package com.insy2s.bibliotheque.controller;

import com.insy2s.bibliotheque.dto.UserRequestPost;
import com.insy2s.bibliotheque.dto.UserResponseGet;
import com.insy2s.bibliotheque.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseGet>> get() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody UserRequestPost urp) {
        userService.userRequestPost(urp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
