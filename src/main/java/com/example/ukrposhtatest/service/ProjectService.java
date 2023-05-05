package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Project;

public interface ProjectService {
    Project save(Project project);

    Project findById(Long id);

    void deleteById(Long id);
}
