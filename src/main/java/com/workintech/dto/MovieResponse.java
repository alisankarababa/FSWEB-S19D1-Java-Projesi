package com.workintech.dto;

import com.workintech.entity.Movie;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class MovieResponse {

    private long id;
    private String name;
    private String directorName;
    private double rating;
    private LocalDate releaseDate;

    private MovieResponse() {

    }

    private MovieResponse(long id, String name, String directorName, double rating, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.directorName = directorName;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public static MovieResponse convert(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getName(), movie.getDirectorName(), movie.getRating(), movie.getReleaseDate());
    }

    public static List<MovieResponse> convert(List<Movie> movies) {

        List<MovieResponse> movieResponses = new ArrayList<>();

        movies.forEach(movie -> {

            MovieResponse movieResponse = MovieResponse.convert(movie);
            movieResponses.add(movieResponse);
        });

        return movieResponses;
    }

}
