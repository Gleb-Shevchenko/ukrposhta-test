package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Sprint;

public interface SprintService {
    Sprint save(Sprint sprint);

    Sprint findById(Long id);

    void deleteById(Long id);
}
