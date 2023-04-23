package com.example.eventmanagment;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.eventmanagment.domain.Event;
import com.example.eventmanagment.domain.EventRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EventRepositoryTest {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Test
	public void CreateNewEvent() {
		Event event = new Event("test event", "test event1", "Helsinki", "2024-01-01", "10", "Org1", "Cat1", "10");
		eventRepository.save(event);
		assertThat(event.getId()).isNotNull();
	}
	
	@Test
	public void FindEventById() {
		Event eventbyid = eventRepository.findById((long) 1).orElse(null);
		assertThat (eventbyid.getId()).isNotNull();
	}

}
