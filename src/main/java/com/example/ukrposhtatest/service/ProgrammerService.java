package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Programmer;
import com.example.ukrposhtatest.model.Project;
import com.example.ukrposhtatest.model.Sprint;
import com.example.ukrposhtatest.model.Team;

import java.util.List;

public interface ProgrammerService {
    Programmer save(Programmer programmer);

    Programmer findById(Long id);

    void deleteById(Long id);

    List<Programmer> findAll();

    List<Project> findAllProjectsById(Long id);

    List<Team> findAllTeamsById(Long id);

    List<Sprint> findAllSprintsById(Long id);
}
