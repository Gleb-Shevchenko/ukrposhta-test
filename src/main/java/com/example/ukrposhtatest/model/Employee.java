package com.example.ukrposhtatest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    @ManyToMany
    private List<Project> projects;
    @ManyToMany(mappedBy = "employees")
    private List<Team> teams;
    @ManyToMany
    private Set<Role> roles;

    public Employee(String name, List<Project> projects, List<Team> teams) {
        this.name = name;
        this.projects = projects;
        this.teams = teams;
    }
}
