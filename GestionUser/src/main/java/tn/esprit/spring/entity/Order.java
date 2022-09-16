package tn.esprit.spring.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
int id;
	String currencyPair;
	String orderType;
	String direction;
	String condition;
	Float conditionValue1;
	Float conditionValue2;
	Float price;
	Float SL;
	Float TP;
	Float lot;
	String state;
	Float capital;
	Float percentage;
	
	@JsonIgnore
	@ManyToOne
	User creator;
	
	@JsonIgnore
	@ManyToOne
	User validator;

	
}
