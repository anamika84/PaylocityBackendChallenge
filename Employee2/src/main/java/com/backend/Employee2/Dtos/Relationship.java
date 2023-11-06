package com.backend.Employee2.Dtos;

import lombok.Getter;

@Getter
public enum Relationship {
        None("None", 0),
        Spouse("Spouse", 300),
        DomesticPartner("DomesticPartner", 300),
        Child("Child", 300),
        Elder("Elder", 100);

    private final String id;
    private final int amount;

        Relationship(String id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        public Relationship findById(String id) {
            for (Relationship r : Relationship.values()) {
                if (r.id.equals(id)) {
                    return r;
                }
            }
            return null;
        }
    }

