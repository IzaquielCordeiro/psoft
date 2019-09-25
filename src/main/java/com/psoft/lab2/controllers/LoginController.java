package com.psoft.lab2.controllers;

import com.psoft.lab2.entities.Login;
import com.psoft.lab2.entities.User;
import com.psoft.lab2.services.JWTService;
import com.psoft.lab2.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController
{
    private final String TOKEN_KEY = "raquel eh top";

    @Autowired
    private UserService userService;
    @Autowired
    private JWTService jwtService;

    public LoginController(UserService userService, JWTService jwtService)
    {
        super();
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public AuthorizationToken authenticate(@RequestBody Login login) throws ServletException
    {
        Optional<User> user = userService.getUser(login.getEmail());

        if(user.isEmpty()) throw new ServletException("User not found!");

        if(!user.get().getPassword().equals(login.getPassword())) throw new ServletException("Incorrect Password!");

        String token = Jwts.builder()
                .setSubject(user.get().getEmail())
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                .compact();

        return new AuthorizationToken(token);
    }

    @DeleteMapping("/usuarios")
    public ResponseEntity<User> deleteUser(@RequestHeader("Authorization") String header)
    {
        try
        {
            Optional<User> user = userService.getUser(jwtService.getSubject(header));
            userService.deleteUser(user.get());
            return new ResponseEntity(user, HttpStatus.OK);
        }
        catch (ServletException e)
        {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    public class AuthorizationToken
    {
        private String token;

        public AuthorizationToken(String token)
        {
            this.token = token;
        }

        public String getToken()
        {
            return token;
        }

        public void setToken(String token)
        {
            this.token = token;
        }
    }
}
