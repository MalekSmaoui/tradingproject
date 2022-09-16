package tn.esprit.spring.services;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import tn.esprit.spring.entity.Commentaire;
import tn.esprit.spring.entity.Likee;



public interface CommentaireService {
public void addCommentairetoEvent(Commentaire com, int eventId);
public void EditCommentaire(Commentaire com,int idC);
public void deleteCommentaire(int idC);
public List<Commentaire> commentaires(int eventId);
public void like(int idC, Likee likee);
}
