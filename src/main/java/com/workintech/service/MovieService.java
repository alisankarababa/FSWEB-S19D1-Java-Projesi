package com.workintech.service;

import com.workintech.dto.*;
import com.workintech.entity.Movie;

import java.util.List;

public interface MovieService {

    List<MovieResponse> findAll();
    MovieResponse findById(long id);
//    MovieResponse save(ActorMovieRequestBody actorMovieRequestBody);
    MovieResponse save(MovieRequest movieRequest);
    MovieResponse save(MovieWithActorIdListReqBody movieWithActorIdListReqBody);
    MovieResponse update(long id, Movie movie);
    MovieResponse delete(long id);
    ActorResponse addActor(long movieId, long actorId);
}
