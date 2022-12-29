package com.example.smartgoods_project.entity.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requirments")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Requirment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String requirement;
    private boolean isRuppScheme;
    private LocalDateTime created;
    private LocalDateTime modified;
}
