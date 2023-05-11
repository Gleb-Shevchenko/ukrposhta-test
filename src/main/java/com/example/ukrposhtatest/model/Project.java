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
import org.hibernate.annotations.*;

@Data
@Entity
@Table(name = "projects")
@SQLDelete(sql = "UPDATE table_product SET deleted = true WHERE id=?")
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    @Enumerated(value = EnumType.STRING)
    private Stage stage;
    private boolean deleted = Boolean.FALSE;

    public enum Stage {
        CONCEPT,
        DESIGN,
        DEVELOPMENT,
        TESTING,
        DEPLOYMENT,
        MAINTENANCE
    }
}
