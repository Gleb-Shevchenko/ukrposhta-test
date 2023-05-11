package com.example.ukrposhtatest.model;

import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "programmers")
public class Programmer extends Employee {
    @Enumerated(value = EnumType.STRING)
    private Level level;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    @OneToMany(mappedBy = "programmers")
    private List<Sprint> sprints;

    public Programmer(String name, List<Project> projects, List<Team> teams,
                      Level level, Type type) {
        super(name, projects, teams);
        this.level = level;
        this.type = type;
    }

    public enum Level {
        JUNIOR,
        MIDDLE,
        SENIOR
    }

    public enum Type {
        DEVELOPER,
        QA,
        DEVOPS
    }
}
