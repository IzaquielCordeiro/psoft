package com.psoft.lab2.repositories;

import com.psoft.lab2.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface DisciplineRepository<T, Id extends Serializable>
        extends JpaRepository<Discipline, Long>
{
    List<Discipline> findByOrderByNoteDesc();

    List<Discipline> findByOrderByLikesDesc();
}
