package com.example.ukrposhtatest.service.impl;

import com.example.ukrposhtatest.model.Programmer;
import com.example.ukrposhtatest.repository.ProgrammerRepository;
import com.example.ukrposhtatest.service.ProgrammerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class ProgrammerServiceImpl implements ProgrammerService {
    private final ProgrammerRepository programmerRepository;

    @Override
    public Programmer save(Programmer programmer) {
        return programmerRepository.save(programmer);
    }

    @Override
    public Programmer findById(Long id) {
        return programmerRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Can't find programmer by id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        programmerRepository.deleteById(id);
    }
}
