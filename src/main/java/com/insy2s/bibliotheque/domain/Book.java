package com.insy2s.bibliotheque.domain;

import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.Year;
import java.util.UUID;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Book {
    @EqualsAndHashCode.Exclude @ToString.Exclude private final UUID uuid = UUID.fromString(Timestamp.from(Instant.now()).toString());
    @Setter private String title;
    @Setter private String auteur;
    @Setter private Year year;
}
