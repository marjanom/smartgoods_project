package com.example.smartgoods.entity.models;

import com.example.smartgoods.entity.Helper.Hashing;
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
    final Long VALID_SESSION_TIME = 120 * 1000L;
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


}


//    public static boolean checkIfRuppScheme(String requirement) {
//
//        String[] requiredWords = new String[]{"shall","should","will","with","the","ability","to","be","able","to"};
//
//        if(requirement.contains(requiredWords[0]) || requirement.contains(requiredWords[1]) || requirement.contains(requiredWords[2]) ){
//            return true;
//        } else return false;
//

//    	if(requirement.contains(requiredWords[0])){
//    		int position = requirement.indexOf(requiredWords[0]);
//    		String sub = requirement.substring(position + 6, position + 10);
//    		sub.replaceAll("\\s", "");
//
//    		if(sub.equals("theabilityto")) {
//    			return true;
//    		} else {
//    		    return false;
//    		}
//    	} else { return false; }

//    	if(requirement.contains(requiredWords[0]) || requirement.contains(requiredWords[1]) || requirement.contains(requiredWords[2])) {
//
//    		int[] positions = new int[]{requirement.indexOf(requiredWords[0]), requirement.indexOf(requiredWords[1]), requirement.indexOf(requiredWords[2])};

    //if(positionChecker(requirement, requiredWords, positions)) return true;

//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getRequirement() {
//        return requirement;
//    }
//
//    public void setRequirement(String requirement) {
//        this.requirement = requirement;
//    }
//
//    public String getUserUuid() {
//        return uuid;
//    }
//
//    public void setUserUiod(String uuid) {
//        this.uuid = uuid;
//    }


