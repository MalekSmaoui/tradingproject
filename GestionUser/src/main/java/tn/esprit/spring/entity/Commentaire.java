package tn.esprit.spring.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity(name = "commentaire")
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "commentaire")
@JsonIgnoreProperties({"hibernateLazyInitializer","likee"})
public class Commentaire {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    int idCom;
	
	String commentaire;
	Date dateCreation;
	int nbrlikes;
	@JsonIgnore
	@ManyToOne
	Event event;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch
			= FetchType.LAZY, mappedBy = "commentaire")
	@JsonIgnoreProperties("commentaire")		
	private List<Likee> likes ;
	@ManyToOne
	User user;
	
}
