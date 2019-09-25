package com.psoft.lab2.services;

import com.psoft.lab2.entities.DTOs.DisciplineDTO;
import com.psoft.lab2.entities.Discipline;
import com.psoft.lab2.repositories.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DisciplineService
{
    @Autowired
    DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository)
    {
        this.disciplineRepository = disciplineRepository;
    }

    @PostConstruct
    public void initDiscipline()
    {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<DisciplineDTO>> typeReference = new TypeReference<List<DisciplineDTO>>(){};
        InputStream inputStream = ObjectMapper.class.getResourceAsStream("/src/main/resources/json/disciplines.json");
        {
            try
            {
                List<DisciplineDTO> disciplines = mapper.readValue(inputStream, typeReference);
                addAllDisciplines(disciplines);
            }
            catch (IOException ioe)
            {
                System.out.printf(ioe.getMessage());
            }
        }
    }

    public Optional<Discipline> getDiscipline(Long Id)
    {
        return disciplineRepository.findById(Id);
    }

    public List<Discipline> getDisciplines()
    {
        return disciplineRepository.findAll();
    }

    public List<DisciplineDTO> getDisciplineDTOs()
    {
        List<DisciplineDTO> disciplineDTOList = new ArrayList();
        for(Discipline d : getDisciplines()) disciplineDTOList.add(new DisciplineDTO(d));

        return disciplineDTOList;
    }

    public List<Discipline> addAllDisciplines(List<DisciplineDTO> dtoList)
    {
        List<Discipline> disciplines = new ArrayList<>();

        for (DisciplineDTO dto: dtoList) disciplines.add(new Discipline(dto));

        return disciplineRepository.saveAll(disciplines);
    }

    public boolean like(Long id)
    {
        Optional<Discipline> d = disciplineRepository.findById(id);
        if(d.isPresent())
        {
            d.get().like();
            disciplineRepository.save(d.get());
            return true;
        }
        return false;
    }

    public boolean comment(Long id, String comment)
    {
        Optional<Discipline> d = disciplineRepository.findById(id);
        if(d.isPresent())
        {
            d.get().setComments(comment + " | ");
            disciplineRepository.save(d.get());
            return true;
        }
        return false;
    }

    public boolean setNote(Long id, double note)
    {
        Optional<Discipline> d = disciplineRepository.findById(id);
        if(d.isPresent())
        {
            d.get().setNote(note);
            disciplineRepository.save(d.get());
            return true;
        }
        return false;
    }

    public List<Discipline> getDisciplineRankingByNote()
    {
        return disciplineRepository.findByOrderByNoteDesc();
    }

    public Object getDisciplineRankingByLikes()
    {
        return disciplineRepository.findByOrderByLikesDesc();
    }
}
