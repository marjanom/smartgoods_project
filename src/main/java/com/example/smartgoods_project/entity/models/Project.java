package com.example.smartgoods_project.entity.models;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    private String projectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Requirement> requirements = new ArrayList<>();


    public Project(User user, String projectName) {
        this.user = user;
        this.projectName = projectName;
    }

    public Project(User user, String projectName, Requirement requirement) {
        this.user = user;
        this.projectName = projectName;
        this.requirements.add(requirement);
    }
}
