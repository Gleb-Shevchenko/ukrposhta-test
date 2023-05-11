package com.example.ukrposhtatest.model;

import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Project project;
    @ManyToMany
    private List<Manager> managers;
    @ManyToMany
    private List<Programmer> programmers;
}
