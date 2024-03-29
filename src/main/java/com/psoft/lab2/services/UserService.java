package com.psoft.lab2.services;

import com.psoft.lab2.entities.DTOs.UserDTO;
import com.psoft.lab2.entities.User;
import com.psoft.lab2.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    UserRepository userDAO;

    public UserService(UserRepository userDAO)
    {
        super();
        this.userDAO = userDAO;
    }

    public UserDTO addUser(User u)
    {
        UserDTO responseUser = new UserDTO(u);
        userDAO.save(u);
        return responseUser;
    }

    public Optional<User> getUser(String email)
    {
        return userDAO.findById(email);
    }

    public void deleteUser(User user)
    {
        userDAO.deleteById(user.getEmail());
    }
}
