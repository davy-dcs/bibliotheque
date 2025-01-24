package com.insy2s.bibliotheque.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
public class Member extends Person {
    @Getter @Setter private String email;

    public Member(String firstname, String lastname, String email) {
        super(firstname, lastname);
        this.email = email;
    }
}
