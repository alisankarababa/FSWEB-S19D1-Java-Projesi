package com.workintech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Data
@Entity
@Table(name = "actor", schema = "fsweb_s19d1")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull(message = "first name cannot be null")
    @NotBlank(message = "first name cannot be empty")
    @Length(message = "first name cannot be shorter than 3 characters long", min= 3)
    @Length(message = "first name cannot be longer than 40 characters long", max = 40)
    @Column(name = "first_name")
    private String firstName;


    @NotNull(message = "last name cannot be null")
    @NotBlank(message = "last name cannot be empty")
    @Length(message = "last name cannot be shorter than 3 characters long", min = 3)
    @Length(message = "last name cannot be longer than 40 characters long", max = 40)
    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @NotNull(message = "birth date cannot be null")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "actor_movie",
            schema = "fsweb_s19d1",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}
