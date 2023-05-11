package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Manager;
import com.example.ukrposhtatest.model.Programmer;
import com.example.ukrposhtatest.model.Team;

import java.util.List;

public interface TeamService {
    Team save(Team team);

    Team findById(Long id);

    void deleteById(Long id);

    List<Team> findAll();

    List<Manager> findAllManagersById(Long id);

    List<Programmer> findAllProgrammersById(Long id);
}
