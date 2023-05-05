package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Programmer;

public interface ProgrammerService {
    Programmer save(Programmer programmer);

    Programmer findById(Long id);

    void deleteById(Long id);
}
