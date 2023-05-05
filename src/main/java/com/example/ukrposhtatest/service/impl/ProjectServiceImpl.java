package com.example.ukrposhtatest.service.impl;

import com.example.ukrposhtatest.model.Project;
import com.example.ukrposhtatest.repository.ProjectRepository;
import com.example.ukrposhtatest.service.ProjectService;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Can't find project by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
