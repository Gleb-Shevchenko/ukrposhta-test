package com.example.ukrposhtatest.model;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sprints")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToOne
    private Project project;
    private LocalDate deadline;
    @ManyToOne
    private Programmer programmer;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public enum Status {
        ASSIGNED,
        PROCESSING,
        CHECK,
        DONE
    }
}
