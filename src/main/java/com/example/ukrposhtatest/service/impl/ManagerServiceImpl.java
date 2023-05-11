package com.example.ukrposhtatest.service.impl;

import com.example.ukrposhtatest.model.Manager;
import com.example.ukrposhtatest.model.Project;
import com.example.ukrposhtatest.model.Team;
import com.example.ukrposhtatest.repository.ManagerRepository;
import com.example.ukrposhtatest.service.ManagerService;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    @Override
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager findById(Long id) {
        return managerRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Can't find manager by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    @Override
    public List<Project> findAllProjectsById(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Can't find manager by id: " + id));
        return manager.getProjects();
    }

    @Override
    public List<Team> findAllTeamsById(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Can't find manager by id: " + id));
        return manager.getTeams();
    }
}
