package org.example.model;

import javax.persistence.*;

@Entity
public class Tutor extends Person {

    public Tutor() {
    }

    public Tutor(String name, String lastName) {
        super(name, lastName);
    }

}
