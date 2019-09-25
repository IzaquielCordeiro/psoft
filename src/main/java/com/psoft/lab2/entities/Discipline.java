package com.psoft.lab2.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.psoft.lab2.entities.DTOs.DisciplineDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Discipline
{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double note;
    private String comments;
    private int likes;

    public Discipline(){ super();};

    public Discipline(DisciplineDTO disciplineDTO)
    {
        this.name = disciplineDTO.getName();
        this.comments = "";
    }

    @JsonCreator
    public Discipline(String name, double note, String comments, int likes)
    {
        this.name = name;
        this.note = note;
        this.comments = comments;
        this.likes = likes;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getNote()
    {
        return note;
    }

    public void setNote(double note)
    {
        this.note = note;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments += comments;
    }

    public int getLikes()
    {
        return likes;
    }

    public void setLikes(int likes)
    {
        this.likes = likes;
    }

    public void like()
    {
        this.likes++;
    }
}
