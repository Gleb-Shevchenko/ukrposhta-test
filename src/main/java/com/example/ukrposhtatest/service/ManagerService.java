package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Manager;
import com.example.ukrposhtatest.model.Project;
import com.example.ukrposhtatest.model.Team;

import java.util.List;

public interface ManagerService {
    Manager save(Manager manager);

    Manager findById(Long id);

    void deleteById(Long id);

    List<Manager> findAll();

    List<Project> findAllProjectsById(Long id);

    List<Team> findAllTeamsById(Long id);
}
