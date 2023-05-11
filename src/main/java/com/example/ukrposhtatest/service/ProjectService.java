package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Project;

import java.util.List;

public interface ProjectService {
    Project save(Project project);

    Project findById(Long id);

    void deleteById(Long id);

    List<Project> findAll(boolean isDeleted);
}
