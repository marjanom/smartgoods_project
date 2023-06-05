package com.example.smartgoods_project.entity.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requirements")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String projectName;
    private String requirement;
    private boolean isRuppScheme;


    public Requirement(Long userId, String projectName, String requirement, boolean isRuppScheme) {
        this.userId = userId;
        this.projectName = projectName;
        this.requirement = requirement;
        this.isRuppScheme = isRuppScheme;

    }

    public Requirement(Long userId) {
        this.userId = userId;
    }

}
