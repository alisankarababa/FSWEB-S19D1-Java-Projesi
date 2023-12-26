package com.workintech.controller;

import com.workintech.dto.ActorRequest;
import com.workintech.dto.ActorResponse;
import com.workintech.dto.MovieResponse;
import com.workintech.entity.Actor;
import com.workintech.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {
    private final ActorService actorService;
    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }
    @GetMapping
    public List<ActorResponse> findAll() {
        return actorService.findAll();
    }
    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable long id) {
        return actorService.findById(id);
    }
    @PostMapping
    public ActorResponse save(@RequestBody ActorRequest actorRequest) {
        return actorService.save(actorRequest);
    }
    @PutMapping("/{id}")
    public ActorResponse update(@PathVariable long id, @RequestBody ActorRequest actorRequest) {
        return actorService.update(id, actorRequest);
    }
    @PutMapping("addMovie")
    public MovieResponse addMovie(@RequestParam long actorId, @RequestParam long movieId) {
        return actorService.addMovie(actorId, movieId);
    }

    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable long id) {
        return actorService.delete(id);
    }
}
