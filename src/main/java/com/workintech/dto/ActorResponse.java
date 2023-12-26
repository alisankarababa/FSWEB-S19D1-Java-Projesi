package com.workintech.dto;

import com.workintech.entity.Actor;
import com.workintech.entity.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ActorResponse {

    private long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;

    private ActorResponse() {

    }
    private ActorResponse(long id, String firstName, String lastName, Gender gender, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }
    public static ActorResponse convert(Actor actor) {

        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getGender(), actor.getBirthDate());
    }

    public static List<ActorResponse> convert(List<Actor> actors) {

        List<ActorResponse> actorResponses = new ArrayList<>();

        actors.forEach(actor -> {
            ActorResponse actorResponse = ActorResponse.convert(actor);
            actorResponses.add(actorResponse);
        });

        return actorResponses;
    }

}