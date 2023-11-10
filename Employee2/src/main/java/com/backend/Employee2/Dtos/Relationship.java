package com.backend.Employee2.Dtos;

import lombok.Getter;

@Getter
public enum Relationship {
        None("None", 0),
        Spouse("Spouse", 600),
        DomesticPartner("DomesticPartner", 600),
        Child("Child", 600),
        Elder("Elder", 200);

    private final String id;
    private final int amount;

        Relationship(String id, int amount) {
            this.id = id;
            this.amount = amount;
        }
    }

