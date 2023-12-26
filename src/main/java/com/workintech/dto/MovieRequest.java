package com.workintech.dto;

import com.workintech.entity.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieRequest {

    private String name;
    private String directorName;
    private double rating;
    private LocalDate releaseDate;

    public static Movie convert(MovieRequest movieRequest) {

        Movie movie = new Movie();
        movie.setName(movieRequest.getName());
        movie.setDirectorName(movieRequest.getDirectorName());
        movie.setRating(movieRequest.getRating());
        movie.setReleaseDate(movieRequest.getReleaseDate());
        return movie;
    }

    public static List<Movie> convert(List<MovieRequest> movieRequests) {

        List<Movie> movies = new ArrayList<>();
        for(MovieRequest movieRequest : movieRequests) {
            movies.add(convert(movieRequest));
        }
        return movies;
    }
}
