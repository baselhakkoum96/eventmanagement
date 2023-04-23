package com.example.eventmanagment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.eventmanagment.domain.Event;
import com.example.eventmanagment.domain.EventRepository;
import com.example.eventmanagment.domain.EventUserInfos;
import com.example.eventmanagment.domain.RSVPRepository;
import com.example.eventmanagment.domain.User;
import com.example.eventmanagment.domain.UserRepository;

@Controller
public class EventController {

	@Autowired
	private final EventRepository eventrepository;
	
	@Autowired
	private RSVPRepository rsvpRepository;
	
	@Autowired
	private UserRepository userRepository; 

	public EventController(EventRepository eventrepository) {
		super();
		this.eventrepository = eventrepository;
	}
	
	/*
	  * This method retrieves the currently logged-in user and populates the homepage view with a list of all events in the system,
	  *  along with whether or not the user has RSVP'd to each event. 
	 */
	@GetMapping("/homepage")
	public String homepage(Model model) {
		
		User user = userRepository.findByUsername(getCurrentUser());
		List<Long> attendedEvents = rsvpRepository.findByUser(user).stream().map(event -> event.getEvent().getId()).collect(Collectors.toList());
		List<Event> events = (List<Event>) eventrepository.findAll();
		model.addAttribute("eventsInfos", events.stream()
				.map(event -> new EventUserInfos(event, attendedEvents.contains(event.getId()))).collect(Collectors.toList()));
		return "homepage";
	}

	 //This method creates a new Event object and adds it to the Model, so that it can be populated with user input and saved to the database.
	@GetMapping("/addevent")
	public String addEventFrom(Model model) {
		Event event = new Event();
		model.addAttribute("event", event);
		return "addevent";
	}
	
	//This method receives an Event object populated with user input and saves it to the database.
	@PostMapping("/addevent")
	public String addEvent(@ModelAttribute("event") Event event) {
		eventrepository.save(event);
		return "redirect:/homepage";
	}
	
	//This method retrieves an Event from the database by its unique ID and adds it to the Model, so that its details can be edited on the "editevent" view.
	@GetMapping(value="/event/edit/{id}")
	public String editEvent(@PathVariable("id") Long id, Model model) {
		model.addAttribute("event", eventrepository.findById(id));
		return "editevent";
	}
	
	// This method receives an Event object populated with updated information and saves it to the database.
	@PostMapping(value="/event/save")
	public String updateEvent(Event event) {
		eventrepository.save(event);
		return "redirect:/homepage";
	}
	
	//This method deletes an Event from the database by its unique ID.
	@GetMapping(value="/event/delete/{id}")
	public String deleteEvent (@PathVariable("id") Long id) {
		eventrepository.deleteById(id);
		return "redirect:/homepage";
	}
	
	//This method retrieves the username of the currently logged-in user from the Spring Security Context.
	private String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		return currentPrincipalName;
	}

}
