package com.example.ukrposhtatest.service.impl;

import com.example.ukrposhtatest.model.Team;
import com.example.ukrposhtatest.repository.TeamRepository;
import com.example.ukrposhtatest.service.TeamService;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Can't find Team by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}
