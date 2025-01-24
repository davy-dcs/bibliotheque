package com.insy2s.bibliotheque.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
public class Author extends Person {
    public Author(String firstname, String lastname) {
        super(firstname, lastname);
    }
}
