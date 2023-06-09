package com.example.ukrposhtatest.repository;

import com.example.ukrposhtatest.model.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammerRepository extends JpaRepository<Programmer, Long> {
}
