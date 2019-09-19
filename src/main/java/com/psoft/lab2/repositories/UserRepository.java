package com.psoft.lab2.repositories;

import com.psoft.lab2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository<T, Id extends Serializable> extends JpaRepository<User, String>
{

}
