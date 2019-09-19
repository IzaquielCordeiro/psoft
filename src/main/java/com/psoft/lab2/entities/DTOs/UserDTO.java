package com.psoft.lab2.entities.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.psoft.lab2.entities.User;

import javax.persistence.Entity;

public class UserDTO
{
    private String name;
    private String email;

    @JsonCreator
    public UserDTO(User u)
    {
        this.name = u.getName();
        this.email = u.getEmail();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }
}
