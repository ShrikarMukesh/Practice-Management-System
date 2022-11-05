package com.citiustech.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.citiustech.model.User;
import com.citiustech.model.UserRole;

@Component
public interface UserService {
    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username);

    //delete user by Id
    public void deleteUser(Long userId);

    public List<User> listOfUsers();

	public User getUserById(Long id);
}
