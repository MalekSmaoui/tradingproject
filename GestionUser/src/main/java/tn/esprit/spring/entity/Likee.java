package tn.esprit.spring.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "likee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table (name = "likee")
public class Likee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    int idLikee;
	@JsonBackReference

	@ManyToOne 
	Commentaire commentaire;
	@ManyToOne
	User user;
	
}
