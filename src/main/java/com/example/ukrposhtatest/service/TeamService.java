package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Team;

public interface TeamService {
    Team save(Team team);

    Team findById(Long id);

    void deleteById(Long id);
}
