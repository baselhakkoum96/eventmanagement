package com.example.eventmanagment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rsvp")
public class RSVP {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsvpId;
    
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean attending;

	public RSVP(Event event, User user, boolean attending) {
		super();
		this.event = event;
		this.user = user;
		this.attending = attending;
	}
    
    public RSVP() {
    	super();
    }

	public Long getId() {
		return rsvpId;
	}

	public void setId(Long id) {
		this.rsvpId = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isAttending() {
		return attending;
	}

	public void setAttending(boolean attending) {
		this.attending = attending;
	}
    
	
    

}
