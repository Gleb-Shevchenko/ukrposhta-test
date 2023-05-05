package com.example.ukrposhtatest.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "managers")
public class Manager extends Employee {
    public Manager(String name, List<Project> projects, List<Team> teams) {
        super(name, projects, teams);
    }
}
