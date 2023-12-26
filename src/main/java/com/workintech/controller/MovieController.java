package com.workintech.controller;

import com.workintech.dto.ActorMovieRequestBody;
import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieRequest;
import com.workintech.dto.MovieResponse;
import com.workintech.entity.Movie;
import com.workintech.service.MovieService;
import com.workintech.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping
    public List<MovieResponse> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable long id) {
        return movieService.findById(id);
    }

    @PostMapping
    public MovieResponse save(@RequestBody MovieRequest movieRequest) {
        return movieService.save(movieRequest);
    }
    @PutMapping("/addActor")
    public ActorResponse addActor(@RequestParam long movieId, @RequestParam long actorId) {

        return movieService.addActor(movieId, actorId);
    }

    @PutMapping("/{id}")
    public MovieResponse update(@PathVariable long id, Movie movie) {
        return movieService.update(id, movie);
    }
    @DeleteMapping("/{id}")
    public MovieResponse delete(@PathVariable long id) {
        return movieService.delete(id);
    }
}
