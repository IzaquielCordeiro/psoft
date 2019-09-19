package com.psoft.lab2.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;

@Service
public class JWTService
{
    private UserService userService;

    public JWTService(UserService userService)
    {
        this.userService = userService;
    }

    public boolean hasUser(String authorizationHeader) throws ServletException
    {
        String subject = getSubject(authorizationHeader);

        return userService.getUser(subject).isPresent();
    }

    public String getSubject(String authorizationHeader) throws ServletException
    {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) throw new ServletException("No Token given");

        String token = (authorizationHeader.substring(com.psoft.lab2.filters.TokenFilter.TOKEN_INDEX));

        String sub = null;

        try
        {
            sub = Jwts.parser().setSigningKey("raquel eh top").parseClaimsJws(token).getBody().getSubject();
        }
        catch (SignatureException e)
        {
            throw new ServletException("Token Invalid or Expired!");
        }

        return sub;
    }
}
