package com.bt06.bt06.service.impl;

import com.bt06.bt06.entity.User;
import com.bt06.bt06.repository.UserRepository;
import com.bt06.bt06.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllBy();
    }

    @Override
    public User getUserByid(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> searchByUserName(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }
}
