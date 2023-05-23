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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String requirement;
    private boolean isRuppScheme;
    private String project;

    public Requirement(Long userId, String requirement, boolean isRuppScheme, String project) {
        this.userId = userId;
        this.requirement = requirement;
        this.isRuppScheme = isRuppScheme;
        this.project = project;
    }

    public Requirement(Long userId) {
        this.userId = userId;
    }

}
