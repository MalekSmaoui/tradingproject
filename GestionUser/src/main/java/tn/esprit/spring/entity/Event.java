package tn.esprit.spring.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
int idEvent;
String titre;
String description;
Date dateCreation;
Date dateCloture;
Boolean Solved;
String pieceJointe;
@JsonIgnore
@ManyToOne
User user;
@JsonIgnore
@OneToMany(cascade = CascadeType.PERSIST, fetch
= FetchType.EAGER, mappedBy = "event")
private Set<Commentaire> commentaires ;

}
