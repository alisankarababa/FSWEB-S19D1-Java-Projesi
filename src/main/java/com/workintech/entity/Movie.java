package com.workintech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "movie", schema = "fsweb_s19d1")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull(message = "movie name cannot be null")
    @NotBlank(message = "movie name cannot be empty")
    @Length(message = "movie name cannot be shorter than 3 characters", min = 3)
    @Length(message = "movie name cannot be longer than 40 characters", max = 40)
    @Column(name = "name")
    private String name;

    @NotNull(message = "director name cannot be null")
    @NotBlank(message = "director name cannot be empty")
    @Length(message = "director name cannot be shorter than 3 characters", min = 3)
    @Length(message = "director name cannot be longer than 40 characters", max = 40)
    @Column(name = "director_name")
    private String directorName;

    @NotNull(message = "movie rating cannot be null")
    @Min(message = "rating cannot be less than 0", value = 0)
    @Max(message = "rating cannot be greater than 10", value = 10)
    @Column(name = "rating")
    private double rating;

    @NotNull(message = "movie release date cannot be null")
    @Column(name = "release_date")
    private LocalDate releaseDate;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "actor_movie",
            schema = "fsweb_s19d1",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors = new ArrayList<>();

    public void addActor(Actor actor) {
        actors.add(actor);
    }
}
