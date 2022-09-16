package tn.esprit.spring.services;

import java.util.List;


import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Documentation;
import tn.esprit.spring.entity.Event;



public interface EventService {
public void addEvent(Event event); 
public Event visitEvent(int idEvent);
public List<Event> Events();
public void deleteEvent(int idE);
public void EditEvent(Event e,int idev);
public List<Event> search(String keyword);
	public void validateEvent(int ide);
public  String getCurrentUserLogin();
}
