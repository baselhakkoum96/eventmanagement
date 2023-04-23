package com.example.eventmanagment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.eventmanagment.domain.Event;
import com.example.eventmanagment.domain.EventRepository;
import com.example.eventmanagment.domain.RSVP;
import com.example.eventmanagment.domain.RSVPRepository;
import com.example.eventmanagment.domain.User;
import com.example.eventmanagment.domain.UserRepository;

@Controller
public class RSVPController {
	
	@Autowired
	private final UserRepository userrepository;
	@Autowired
	private final EventRepository eventrepository;
	@Autowired
	private final RSVPRepository rsvprepository;
	
	
	
    public RSVPController(UserRepository userrepository, EventRepository eventrepository,
			RSVPRepository rsvprepository) {
		super();
		this.userrepository = userrepository;
		this.eventrepository = eventrepository;
		this.rsvprepository = rsvprepository;
	}

    /*
     * Handles the "attend" action for a specific event and user.
     * Adds a new RSVP for the given event and user if one doesn't exist already,
     * and increments the count of users for the event.
     */
    @GetMapping("/rsvplist/{userId}/{eventId}/attend")
    public String attendRSVP(@PathVariable("userId") String userId, @PathVariable("eventId") Long eventId) {
        User user = userrepository.findByUsername(userId);
        Event event = eventrepository.findById(eventId).orElse(null);
        RSVP rsvp = rsvprepository.findByEventAndUser(event, user);
        if(rsvp == null) {
        	rsvp = new RSVP(event, user, true);
        	rsvprepository.save(rsvp);
			event.setCountOfUsers(event.getCountOfUsers() + 1);
			eventrepository.save(event);
        }
        return "redirect:/homepage";
    }
    
    /*
     * Handles the "unattending" action from an specified user from a specified event.
     * Removes a RSVP for the given event and user of it exists.
     * and decrease the count of users for the event.
     */
    @GetMapping("/rsvplist/{userId}/{eventId}/remove")
    public String unattendRSVP(@PathVariable("userId") String userId, @PathVariable("eventId") Long eventId) {
        User user = userrepository.findByUsername(userId);
        Event event = eventrepository.findById(eventId).orElse(null);
        RSVP rsvp = rsvprepository.findByEventAndUser(event, user);
        if(rsvp != null) {
        	rsvprepository.delete(rsvp);
        	event.setCountOfUsers(event.getCountOfUsers() - 1);
        	eventrepository.save(event);
        }
        return "redirect:/homepage";
    }
    

}
