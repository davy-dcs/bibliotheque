package com.insy2s.bibliotheque.service;

import com.insy2s.bibliotheque.domain.User;
import com.insy2s.bibliotheque.dto.UserRequestPost;
import com.insy2s.bibliotheque.dto.UserResponseGet;
import com.insy2s.bibliotheque.dto.mapper.UserMapper;
import com.insy2s.bibliotheque.exception.UserNotFoundException;
import com.insy2s.bibliotheque.repository.IUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final IUserRepository userRepository;

    public User getByUuid(UUID uuid) {
        Optional<User> userOptional = userRepository.findByUuid(uuid);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException("User not found");
    }

    public List<UserResponseGet> getAllUsers() {
        return UserMapper.usersResponseGet(userRepository.findAll());
    }

    public void userRequestPost(UserRequestPost urp) {
        @Valid User user = UserMapper.userRequestPost(urp);
        userRepository.save(user);
    }

}
