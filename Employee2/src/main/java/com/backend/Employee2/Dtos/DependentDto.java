package com.backend.Employee2.Dtos;

import com.backend.Employee2.Model.Dependent;
import lombok.Data;

import java.util.Date;

@Data
public class DependentDto {
        public String  firstName;
        public String lastName ;

    public DependentDto(String firstName, String lastName, Date dateOfBirth, Relationship relationship) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.relationship = relationship;
    }

    public Date dateOfBirth;
        public Relationship relationship ;
        public static DependentDto from(Dependent dependent) {
            DependentDto dependentDto = new DependentDto(dependent.getFirstName(), dependent.getLastName(),dependent.getDateOfBirth(),Relationship.valueOf(dependent.getRelationType()));
            if(dependent != null) {
                dependentDto.setFirstName(dependent.getFirstName());
                dependentDto.setLastName(dependentDto.getLastName());
                dependentDto.setDateOfBirth(dependent.getDateOfBirth());
                dependentDto.setRelationship(Relationship.valueOf(dependent.getRelationType()));
            }
            return dependentDto;
        }
    }
