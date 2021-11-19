package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.user.User;
import com.techelevator.tenmo.model.user.UserNotFoundException;

import java.util.List;

public interface UserDAO {

    List<User> findAll();
    
    User getUserById(Long userId) throws UserNotFoundException;

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);
}
