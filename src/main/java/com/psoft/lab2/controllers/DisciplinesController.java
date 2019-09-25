package com.psoft.lab2.controllers;

import com.psoft.lab2.entities.DTOs.DisciplineDTO;
import com.psoft.lab2.entities.Discipline;
import com.psoft.lab2.services.DisciplineService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.List;

@RequestMapping("/disciplinas")
@RestController
public class DisciplinesController
{
    @Autowired
    DisciplineService disciplineService;

    public DisciplinesController(DisciplineService disciplineService)
    {
        super();
        this.disciplineService = disciplineService;
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<Discipline>> addDisciplines(@RequestBody List<DisciplineDTO> disciplineList)
    {
        List<Discipline> disciplines = disciplineService.addAllDisciplines(disciplineList);
        return new ResponseEntity(disciplines, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<DisciplineDTO>> getDisciplines() throws ServletException
    {
        return new ResponseEntity(disciplineService.getDisciplineDTOs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id)
    {
        return new ResponseEntity(disciplineService.getDiscipline(id), HttpStatus.OK);
    }

    @PutMapping("/likes/{id}")
    public ResponseEntity<Discipline> like(@PathVariable Long id)
    {
        if(disciplineService.like(id)) return new ResponseEntity(disciplineService.getDiscipline(id), HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/nota/{id}")
    public ResponseEntity<Discipline> setNote(@PathVariable Long id, @RequestBody JSONObject json)
    {
        if(disciplineService.setNote(id, Double.parseDouble(json.get("nota").toString()))) return new ResponseEntity(disciplineService.getDiscipline(id), HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/comentarios/{id}")
    public ResponseEntity<Discipline> comment(@PathVariable Long id, @RequestBody JSONObject json)
    {
        if(disciplineService.comment(id, json.get("comentario").toString())) return new ResponseEntity(disciplineService.getDiscipline(id), HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ranking/notas")
    public ResponseEntity<List<Discipline>> rankingNotes()
    {
        return new ResponseEntity(disciplineService.getDisciplineRankingByNote(), HttpStatus.OK);
    }

    @GetMapping("/ranking/likes")
    public ResponseEntity<List<Discipline>> rankingLikes()
    {
        return new ResponseEntity(disciplineService.getDisciplineRankingByLikes(), HttpStatus.OK);
    }
}
