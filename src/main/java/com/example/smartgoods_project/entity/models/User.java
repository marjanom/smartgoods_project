package com.example.smartgoods_project.entity.models;

import com.example.smartgoods_project.helper.Hashing;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    final Long VALID_SESSION_TIME = 12000* 1000L;
    String username;
    String firstName;
    String lastName;
    int failedLoginCounter = 0;
    Long lockedUntil = null;
    Long sessionValidUntil;
    UUID session;
    private byte[] password;
    private byte[] salt;

    public User(Long id, String username, String firstName, String lastName, int failedLoginCounter, String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.failedLoginCounter = failedLoginCounter;
        ArrayList<byte[]> list = Hashing.generateHash(password);
        this.salt = list.get(0);
    }

    public void setPassword(String password) {
        ArrayList<byte[]> list = Hashing.generateHash(password);
        this.salt = list.get(0);
        this.password = list.get(1);
    }

    public void refreshSession() {
        this.sessionValidUntil = new Date().getTime() + VALID_SESSION_TIME;
    }
}




