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

    @Column(length = 500)
    private String requirement;
    private boolean isRuppScheme;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    public Requirement(Long userId, Project project, String requirement, boolean isRuppScheme) {
        this.userId = userId;
        this.project = project;
        this.requirement = requirement;
        this.isRuppScheme = isRuppScheme;

    }

    public Requirement(Long userId) {
        this.userId = userId;
    }

}
