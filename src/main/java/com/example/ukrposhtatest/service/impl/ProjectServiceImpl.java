package com.example.ukrposhtatest.service.impl;

import com.example.ukrposhtatest.model.Project;
import com.example.ukrposhtatest.repository.ProjectRepository;
import com.example.ukrposhtatest.service.ProjectService;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final EntityManager entityManager;

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
        Project project = projectRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Can't find project by id: " + id));
        project.setDeleted(true);
        projectRepository.save(project);
    }

    @Override
    public List<Project> findAll(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Project> projects =  projectRepository.findAll();
        session.disableFilter("deletedProductFilter");
        return projects;
    }
}
