package com.example.smartgoods_project.entity.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userIdentity;
    private String projectName;

    private String project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Requirement> requirements = new ArrayList<>();

    private String requirement;

    public Project(Long userIdentity, String projectName) {
        this.userIdentity = userIdentity;
        this.projectName = projectName;
    }

    public Project(Long userIdentity, String projectName, String requirement) {
        this.userIdentity = userIdentity;
        this.projectName = projectName;
        this.requirement = requirement;
    }
}
