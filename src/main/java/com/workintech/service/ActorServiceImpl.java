package com.workintech.service;

import com.workintech.dto.ActorRequest;
import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieResponse;
import com.workintech.entity.Actor;
import com.workintech.entity.Movie;
import com.workintech.exception.CustomException;
import com.workintech.repository.ActorRepository;
import com.workintech.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService{
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }
    @Override
    public List<ActorResponse> findAll() {
        return ActorResponse.convert(actorRepository.findAll());
    }
    @Override
    public ActorResponse findById(long id) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (optionalActor.isEmpty())
            throw new CustomException(String.format("actor with id %d cannot be found", id), HttpStatus.NOT_FOUND);

        return ActorResponse.convert(optionalActor.get());
    }

    @Override
    public ActorResponse save(ActorRequest actorRequest) {
        return ActorResponse.convert(actorRepository.save(ActorRequest.convert(actorRequest)));
    }

    @Override
    public ActorResponse update(long id, ActorRequest actorRequest) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (optionalActor.isEmpty())
            throw new CustomException(String.format("actor with id %d cannot be found", id), HttpStatus.NOT_FOUND);

        Actor oldActor = optionalActor.get();
        Actor updatedActor = ActorRequest.convert(actorRequest);

        updatedActor.setId(id);
        updatedActor.setMovies(oldActor.getMovies());

        return ActorResponse.convert(actorRepository.save(updatedActor));
    }

    @Override
    public MovieResponse addMovie(long actorId, long movieId) {

        Optional<Actor> optionalActor = actorRepository.findById(actorId);
        if(optionalActor.isEmpty())
            throw new CustomException(String.format("Actor with id %d cannot be found", actorId), HttpStatus.NOT_FOUND);

        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if(optionalMovie.isEmpty())
            throw new CustomException(String.format("Movie with id %d cannot be found", movieId), HttpStatus.NOT_FOUND);

        Actor actor = optionalActor.get();
        Movie movie = optionalMovie.get();

        if(actor.getMovies().contains(movie))
            throw new CustomException(String.format("Actor with id %d already contains movie with id %d", actorId, movieId), HttpStatus.BAD_REQUEST);

        actor.addMovie(movie);
        movie.addActor(actor);
        actorRepository.save(actor);
        return MovieResponse.convert(movie);
    }

    @Override
    public ActorResponse delete(long id) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (optionalActor.isEmpty())
            throw new CustomException(String.format("actor with id %d cannot be found", id), HttpStatus.NOT_FOUND);

        actorRepository.deleteById(id);
        return ActorResponse.convert(optionalActor.get());
    }
}
