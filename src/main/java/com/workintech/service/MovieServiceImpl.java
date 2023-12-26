package com.workintech.service;

import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieRequest;
import com.workintech.dto.MovieResponse;
import com.workintech.dto.MovieWithActorIdListReqBody;
import com.workintech.entity.Movie;
import com.workintech.entity.Actor;
import com.workintech.exception.CustomException;
import com.workintech.repository.ActorRepository;
import com.workintech.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }
    @Override
    public List<MovieResponse> findAll() {

        return MovieResponse.convert(movieRepository.findAll());
    }

    @Override
    public MovieResponse findById(long id) {

        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isEmpty())
            throw new CustomException(String.format("movie with id %d is not found", id), HttpStatus.NOT_FOUND);

        return MovieResponse.convert(optionalMovie.get());
    }

//    @Override
//    public MovieResponse save(ActorMovieRequestBody actorMovieRequestBody) {
//
//        Actor actor = actorMovieRequestBody.getActor();
//        if(actor == null)
//            throw new CustomException("Actor cannot be null", HttpStatus.BAD_REQUEST);
//
//        Movie movie = actorMovieRequestBody.getMovie();
//        if(movie == null)
//            throw new CustomException("Movie cannot be null", HttpStatus.BAD_REQUEST);
//
//        movie.addActor(actor);
//
//        return MovieResponse.convert(movieRepository.save(movie));
//    }

    @Override
    public MovieResponse save(MovieRequest movieRequest) {
        return MovieResponse.convert(movieRepository.save(MovieRequest.convert(movieRequest)));
    }

    @Override
    public MovieResponse save(MovieWithActorIdListReqBody movieWithActorIdListReqBody) {

        List<Long> idListActors = movieWithActorIdListReqBody.getIdListActors();
        Movie movie = movieWithActorIdListReqBody.getMovie();

        if(idListActors == null || idListActors.isEmpty())
            throw new CustomException("list of ids cannot be null or empty", HttpStatus.BAD_REQUEST);
        if(movie == null)
            throw new CustomException("movie cannot be null", HttpStatus.BAD_REQUEST);

        for (Long id: idListActors ) {
            Optional<Actor> optionalActor = actorRepository.findById(id);
            if(optionalActor.isEmpty())
                throw new CustomException("actor with id " + id + "cannot be found to add to actorlist of the movie", HttpStatus.BAD_REQUEST);

            movie.addActor(optionalActor.get());
        }

        return MovieResponse.convert(movieRepository.save(movie));
    }

    @Override
    public MovieResponse update(long id, Movie movie) {
        movie.setId(id);
        return MovieResponse.convert(movieRepository.save(movie));
    }

    @Override
    public MovieResponse delete(long id) {

        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isEmpty())
            throw new CustomException(String.format("movie with id %d is not found", id), HttpStatus.NOT_FOUND);

        movieRepository.deleteById(id);

        return MovieResponse.convert(optionalMovie.get());
    }

    public ActorResponse addActor(long movieId, long actorId) {

        Optional<Actor> optionalActor = actorRepository.findById(actorId);
        if(optionalActor.isEmpty())
            throw new CustomException(String.format("Actor with id %d cannot be found", actorId), HttpStatus.NOT_FOUND);

        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if(optionalMovie.isEmpty())
            throw new CustomException(String.format("Movie with id %d cannot be found", movieId), HttpStatus.NOT_FOUND);

        Actor actor = optionalActor.get();
        Movie movie = optionalMovie.get();

        if (movie.getActors().contains(actor))
            throw new CustomException(String.format("Movie with id %d already contains actor with id %d", movieId, actorId), HttpStatus.BAD_REQUEST);

        movie.addActor(actor);
        movieRepository.save(movie);
        return ActorResponse.convert(actor);
    }


}
