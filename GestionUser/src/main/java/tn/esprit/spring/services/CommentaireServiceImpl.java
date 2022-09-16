package tn.esprit.spring.services;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Commentaire;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Likee;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.CommentaireRepository;
import tn.esprit.spring.repositories.EventRepository;
import tn.esprit.spring.repositories.IUserRepository;
import tn.esprit.spring.repositories.LikeeRepository;




@Service
public class CommentaireServiceImpl implements CommentaireService {
@Autowired 
LikeeRepository likeeRepository;
	@Autowired 
	EventRepository eventRepository;
	@Autowired
	CommentaireRepository commentaireRepository;
@Autowired
IUserRepository userRepository;
@Autowired 
IUserService userService;
	@Override
	public void EditCommentaire(Commentaire com,int idC) {
		Commentaire c=commentaireRepository.findById(idC).orElse(null);
        c.setCommentaire(com.getCommentaire());
       
        c.setDateCreation(com.getDateCreation());
       c.setLikes(com.getLikes());
      
       commentaireRepository.save(c);
		
	}

 	@Override
	public void deleteCommentaire(int idC) {
		Commentaire c=commentaireRepository.findById(idC).orElse(null);
     commentaireRepository.delete(c);
		
	}

	@Override
	public List<Commentaire> commentaires(int eventId) {
		return commentaireRepository.commentaires(eventId);	
	 
	}

	@Override
	public void like(int idC, Likee likee ) {
		Commentaire c=commentaireRepository.findById(idC).orElse(null);
		//likee.setCommentaire(c);
		c.setNbrlikes(c.getNbrlikes()+1); 
		commentaireRepository.save(c);
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User u= userService.retrieveUserByUsername(username);
        likee.setUser(u);
		likeeRepository.save(likee);
	}

	@Override
	public void addCommentairetoEvent(Commentaire com, int eventId) {
		Event e= eventRepository.findById(eventId).orElse(null);
		com.setEvent(e);
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User u= userService.retrieveUserByUsername(username);
		com.setUser(u);
		commentaireRepository.save(com);
		
		
	}

	
}
