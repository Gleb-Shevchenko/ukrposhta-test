package com.example.ukrposhtatest.service;

import com.example.ukrposhtatest.model.Manager;
import java.util.List;

public interface ManagerService {
    Manager save(Manager manager);

    Manager findById(Long id);

    void deleteById(Long id);

    List<Manager> findAll();
}
