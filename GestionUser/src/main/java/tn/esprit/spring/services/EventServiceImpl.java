package tn.esprit.spring.services;

import java.io.Console;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.BestPractice;
import tn.esprit.spring.entity.Commentaire;
import tn.esprit.spring.entity.Documentation;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.EventRepository;
import tn.esprit.spring.repositories.IUserRepository;


@Service
public class EventServiceImpl implements EventService{
@Autowired
EventRepository eventRepository;
@Autowired
IUserRepository userRepository;
@Autowired IUserService userService;
	@Override
	public void addEvent(Event event) {
		event.setSolved(false);
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User u= userService.retrieveUserByUsername(username);
		    event.setUser(u);
			eventRepository.save(event);
  
		    
	}
	
	@Override	
	public void validateEvent(int ide) {
		Event e= eventRepository.findById(ide).orElse(null);
       e.setSolved(true);		
      eventRepository.save(e);
	}
	@Override
	public Event visitEvent(int idEvent) {
		
		return eventRepository.findById(idEvent).orElse(null);
	}

	@Override
	public List<Event> Events() {
		
		return eventRepository.findAll();
	}
	@Override
	public void EditEvent(Event e, int idev) {
		Event ev= eventRepository.findById(idev).orElse(null);
	ev.setTitre(e.getTitre());
	ev.setDescription(e.getDescription());
	ev.setPieceJointe(e.getPieceJointe());

	eventRepository.save(ev);
	}

	@Override
	public List<Event> search(String keyword) {
		// TODO Auto-generated method stub
		return eventRepository.search(keyword);
	}
 	@Override
	public void deleteEvent(int idE) {
		Event e=eventRepository.findById(idE).orElse(null);
     eventRepository.delete(e);
		
	}


	@Override
	public String getCurrentUserLogin() {
		 SecurityContext securityContext = SecurityContextHolder.getContext();
		    Authentication authentication = securityContext.getAuthentication();
		    String userName = null;
		    if (authentication != null) {
		        if (authentication.getPrincipal() instanceof UserDetails) {
		            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
		            userName = springSecurityUser.getUsername();
		        } else if (authentication.getPrincipal() instanceof String) {
		            userName = (String) authentication.getPrincipal();
		        }
		    }
		    return userName;
	}



}
