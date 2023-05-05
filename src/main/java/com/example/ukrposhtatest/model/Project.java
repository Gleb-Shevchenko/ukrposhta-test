package com.example.ukrposhtatest.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    @Enumerated(value = EnumType.STRING)
    private Stage stage;

    public enum Stage {
        CONCEPT,
        DESIGN,
        DEVELOPMENT,
        TESTING,
        DEPLOYMENT,
        MAINTENANCE
    }
}
