package ru.job4j.service;

import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;

public class UserService {

    public UserService() {
    }

    private static final class LazyUser {
        private static final UserService INST = new UserService();
    }

    public static UserService instOf() {
        return LazyUser.INST;
    }

    public User addUser(User user) {
        return UserRepository.instOf().addUser(user);
    }

    public User findUserByEmail(String email) {
        return UserRepository.instOf().findUserByEmail(email);
    }
}
