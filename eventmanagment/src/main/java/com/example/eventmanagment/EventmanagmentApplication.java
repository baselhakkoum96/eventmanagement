package com.example.eventmanagment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.eventmanagment.domain.Event;
import com.example.eventmanagment.domain.EventRepository;
import com.example.eventmanagment.domain.User;
import com.example.eventmanagment.domain.UserRepository;

@SpringBootApplication
public class EventmanagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventmanagmentApplication.class, args);

	}

	@Bean
	public CommandLineRunner demo(@Autowired UserRepository userrepository,
			@Autowired EventRepository eventRepository) {
		return (args) -> {
			//Creation of Admin and User
			User admin = new User("admin", "admin@admin.com",
					"$2a$10$mCZ/Gvr6ohHUnExdZST6fuLgkI9akogckzmslvspP7DrBUaTcZdny", "admin", "admin", "+358-40-656565",
					"1986-02-22", "Helsinki", "ADMIN");
			userrepository.save(admin);
			User user = new User("user", "user@user.com",
					"$2a$10$uqPhqMqTAYyZY1KIHa3lxu7sEZ5LsmEQbkFOAbUSKpP3Czrdq5bDq", "user", "user", "+358-40-5555555",
					"1978-02-22", "Helsinki", "USER");
			userrepository.save(user);
			
			//Creation of 3 events
			Event event = new Event("event1", "event1", "Helsinki", "2000-01-01", "10", "Org1", "Cat1", "10");
			Event event2 = new Event("event2", "event2", "Helsinki", "2000-01-01", "10", "Org2", "Cat2", "10");
			Event event3 = new Event("event3", "event3", "Helsinki", "2000-01-01", "10", "Org2", "Cat4", "11");
			eventRepository.save(event);
			eventRepository.save(event2);
			eventRepository.save(event3);
			
		};
	}
}
