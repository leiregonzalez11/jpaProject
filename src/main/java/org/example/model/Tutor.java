package org.example.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "TU")
public class Tutor extends Person {

    public Tutor() {
    }

    public Tutor(String name, String lastName) {
        super(name, lastName);
    }

}
