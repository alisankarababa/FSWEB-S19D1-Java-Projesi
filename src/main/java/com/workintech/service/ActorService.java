package com.workintech.service;

import com.workintech.dto.ActorRequest;
import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieResponse;
import com.workintech.entity.Actor;

import java.util.List;

public interface ActorService {

    List<ActorResponse> findAll();
    ActorResponse findById(long id);
    ActorResponse save(ActorRequest actorRequest);
    ActorResponse update(long id, ActorRequest actorRequest);
    MovieResponse addMovie(long actorId, long movieId);
    ActorResponse delete(long id);

}
