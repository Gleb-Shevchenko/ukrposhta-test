package com.example.ukrposhtatest.service.impl;

import com.example.ukrposhtatest.model.Sprint;
import com.example.ukrposhtatest.repository.SprintRepository;
import com.example.ukrposhtatest.service.SprintService;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SprintServiceImpl implements SprintService {
    private final SprintRepository sprintRepository;

    @Override
    public Sprint save(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint findById(Long id) {
        return sprintRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Can't find Sprint by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        sprintRepository.deleteById(id);
    }
}
