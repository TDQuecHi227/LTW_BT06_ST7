package com.bt06.bt06.service;

import com.bt06.bt06.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserByid(long id);
    void saveUser(User user);
    void deleteUser(User user);
    User getUserByUsername(String username);
    List<User> searchByUserName(String username);
}
