package com.insy2s.bibliotheque.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Book {
    @Id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private UUID uuid;

    @Column(nullable = false)
    @NotBlank(message = "Title is mandatory.")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Author is mandatory.")
    private String author;

    @EqualsAndHashCode.Exclude
    @Column(nullable = false)
    private boolean isAvailable;

    @OneToMany(mappedBy = "book")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Borrowing> borrowings = new ArrayList<>();

    @PrePersist()
    void create(){
        uuid = UUID.randomUUID();
        isAvailable = true;
    }

}
