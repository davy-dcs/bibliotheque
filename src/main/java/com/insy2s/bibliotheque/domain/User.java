package com.insy2s.bibliotheque.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @EqualsAndHashCode.Exclude
    @Column(length = 50, nullable = false)
    @NotBlank(message = "Name is mandatory.")
    @Size(max = 50)
    private String name;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Borrowing> borrowings = new ArrayList<>();
}
