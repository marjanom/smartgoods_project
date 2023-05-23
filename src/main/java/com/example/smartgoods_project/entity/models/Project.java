package com.example.smartgoods_project.entity.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String projectName;
    private String requirment;

    public Project(Long userId, String project) {
        this.userId = userId;
        this.projectName = project;
    }
}
