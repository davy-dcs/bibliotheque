package com.insy2s.bibliotheque.dto.mapper;

import com.insy2s.bibliotheque.domain.User;
import com.insy2s.bibliotheque.dto.UserRequestPost;
import com.insy2s.bibliotheque.dto.UserResponseGet;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserResponseGet userResponseGet(User user) {
        return new UserResponseGet(user.getUuid(), user.getName(), user.getEmail());
    }

    public static List<UserResponseGet> usersResponseGet(List<User> users) {
        List<UserResponseGet> response = new ArrayList<>();
        for (User user : users) {
            response.add(userResponseGet(user));
        }
        return response;
    }

    public static User userRequestPost(UserRequestPost urp) {
        User user = new User();
        user.setName(urp.name());
        user.setEmail(urp.email());
        return user;
    }
}
