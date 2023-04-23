package com.example.eventmanagment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id", nullable = false, updatable = false)
	private Long event_id;
	@Column(name = "eventtitle", nullable = false)
	private String eventtitle;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "location", nullable = false)
	private String location;
	@Column(name = "date", nullable = false)
	private String date;
	@Column(name = "time", nullable = false)
	private String time;
	@Column(name = "organizer", nullable = false)
	private String organizer;
	@Column(name = "category", nullable = false)
	private String category;
	@Column(name = "duration", nullable = false)
	private String duration;
	@Column(name = "count_of_users", nullable = false)
	private Long countOfUsers;

	public Event(String eventtitle, String description, String location, String date, String time, String organizer,
			String category, String duration) {
		super();
		this.eventtitle = eventtitle;
		this.description = description;
		this.location = location;
		this.date = date;
		this.time = time;
		this.organizer = organizer;
		this.category = category;
		this.duration = duration;
		this.countOfUsers = 0L;
	}
	
	

	public Event() {
		super();
		this.countOfUsers=0L;
	}

	public Long getId() {
		return event_id;
	}

	public void setId(Long id) {
		this.event_id = id;
	}

	public String getEventtitle() {
		return eventtitle;
	}

	public void setEventtitle(String eventtitle) {
		this.eventtitle = eventtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Long getCountOfUsers() {
		return countOfUsers;
	}

	public void setCountOfUsers(Long countOfUsers) {
		this.countOfUsers = countOfUsers;
	}

}
