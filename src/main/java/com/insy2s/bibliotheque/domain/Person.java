package com.insy2s.bibliotheque.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Getter
public abstract class Person {
    private final UUID uuid = UUID.fromString(Timestamp.from(Instant.now()).toString());
    @Setter protected String firstname;
    @Setter protected String lastname;
}
