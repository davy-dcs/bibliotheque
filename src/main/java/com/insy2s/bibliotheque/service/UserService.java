package com.insy2s.bibliotheque.service;

import com.insy2s.bibliotheque.domain.User;
import com.insy2s.bibliotheque.repository.IUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final IUserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(@Valid User user) {
        userRepository.save(user);
    }

}
