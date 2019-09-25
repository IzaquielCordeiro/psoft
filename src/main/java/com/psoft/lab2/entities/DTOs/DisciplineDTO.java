package com.psoft.lab2.entities.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.psoft.lab2.entities.Discipline;

public class DisciplineDTO
{
    private String name;
    private Long Id;

    @JsonCreator
    public DisciplineDTO(String name)
    {
        this.name = name;
    }

    public DisciplineDTO(Discipline d)
    {
        this.name = d.getName();
        this.Id = d.getId();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }
}
