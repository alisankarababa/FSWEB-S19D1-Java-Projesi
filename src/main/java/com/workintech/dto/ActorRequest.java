package com.workintech.dto;

import com.workintech.entity.Actor;
import com.workintech.entity.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Data
@NoArgsConstructor
public class ActorRequest {
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;

    public static Actor convert(ActorRequest actorRequest) {

        Actor actor = new Actor();
        actor.setFirstName(actorRequest.getFirstName());
        actor.setLastName(actorRequest.getLastName());
        actor.setGender(actorRequest.getGender());
        actor.setBirthDate(actorRequest.getBirthDate());
        return actor;
    }

    public static List<Actor> convert(List<ActorRequest> actorRequests) {

        List<Actor> actors = new ArrayList<>();
        for(ActorRequest actorRequest : actorRequests) {
            actors.add(convert(actorRequest));
        }
        return actors;
    }
}
