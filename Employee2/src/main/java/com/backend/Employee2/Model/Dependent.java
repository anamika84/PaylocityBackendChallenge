package com.backend.Employee2.Model;


import com.backend.Employee2.Dtos.DependentDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

    @Data
    @Entity
    @Table(name= "DEPENDENT")
    public class Dependent{

        @jakarta.persistence.Id
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String firstName;
        private String lastName;
        private Date dateOfBirth;
        private String relationType;
//        @ManyToOne(cascade = CascadeType.MERGE)
//        @JoinColumn(name="employee_id")
//        private Employee employee;

        public static Dependent from(DependentDto dependentsDto) {
            Dependent dependent = new Dependent();
            dependent.setFirstName(dependentsDto.getFirstName());
            dependent.setLastName(dependentsDto.getLastName());
            dependent.setRelationType(String.valueOf(dependentsDto.getRelationship()));
            dependent.setDateOfBirth(dependentsDto.getDateOfBirth());
            return dependent;
        }
    }
