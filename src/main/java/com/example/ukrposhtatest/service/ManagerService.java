package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Manager;

public interface ManagerService {
    Manager save(Manager manager);

    Manager findById(Long id);

    void deleteById(Long id);
}
