package com.example.smartgoods_project.entity.models;

import lombok.*;
import javax.persistence.*;

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

    String uuid;
    String username;

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


