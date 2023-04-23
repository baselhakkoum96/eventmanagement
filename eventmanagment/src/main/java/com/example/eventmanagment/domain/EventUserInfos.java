package com.example.eventmanagment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

/*used lombok dependency for this class
 * the lombok dependency has lots of features but it's used here for setters, getters and AllArgsconstructor
 * in this class were are getting the event and a boolean of attended to use them for the implementation of attending and unattending from an event
 */
public class EventUserInfos {

	private Event event;

	private Boolean attended;

}
