package com.example.eventmanagment.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface RSVPRepository extends CrudRepository<RSVP, Long> {

    RSVP findByEventAndUser(Event event, User user);

    List<RSVP> findByEvent(Event event);

    int countByEventAndAttending(Event event, boolean attending);
    
    List<RSVP> findByUser(User user);

}
