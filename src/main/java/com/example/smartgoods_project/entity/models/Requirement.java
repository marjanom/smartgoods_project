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
    private String project;
    private String requirement;
    private boolean isRuppScheme;


    public Requirement(Long userId, String requirement, boolean isRuppScheme) {
        this.userId = userId;
        this.requirement = requirement;
        this.isRuppScheme = isRuppScheme;

    }

    public Requirement(Long userId) {
        this.userId = userId;
    }

}
