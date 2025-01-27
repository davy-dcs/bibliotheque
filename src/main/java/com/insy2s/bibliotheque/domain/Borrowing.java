package com.insy2s.bibliotheque.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDate borrowDate = LocalDate.now();

    @FutureOrPresent
    private LocalDate returnDate;
}
