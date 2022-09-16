package tn.esprit.spring.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table (name = "T_USER")
public class User implements Serializable , UserDetails {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	 int idUser ;
	protected String username ;
	protected String fullName ;
	private String emailUser;
	protected String cinUser ;
	protected String password ;
	private Boolean verified;
	int numberOfpoints;
	int numberOfbadges;

	protected String phoneNumberUser ;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@JsonIgnore

	@OneToMany(cascade = CascadeType.ALL)
	private Set<ImageUser> ImageUser1;
	
	@JsonIgnore

	@OneToMany(cascade = CascadeType.PERSIST, fetch
	= FetchType.EAGER, mappedBy = "creator")
	private Set<Order> orders;
	
	@JsonIgnore

	@OneToMany(cascade = CascadeType.PERSIST, fetch
	= FetchType.EAGER, mappedBy = "validator")
	private Set<Order> orderss;
	
	@JsonIgnore

	@OneToMany(cascade = CascadeType.PERSIST, fetch
	= FetchType.EAGER, mappedBy = "user")
	private Set<Event> events;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch
	= FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Set<Likee> likes;
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch
			= FetchType.EAGER, mappedBy = "user")
			@JsonIgnore
			private Set<Commentaire> commentaires;
	
	


	
	

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getCinUser() {
		return cinUser;
	}

	public void setCinUser(String cinUser) {
		this.cinUser = cinUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getPhoneNumberUser() {
		return phoneNumberUser;
	}

	public void setPhoneNumberUser(String phoneNumberUser) {
		this.phoneNumberUser = phoneNumberUser;
	}

	
	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	

	public Set<ImageUser> getImageUser1() {
		return ImageUser1;
	}

	public void setImageUser1(Set<ImageUser> imageUser1) {
		ImageUser1 = imageUser1;
	}

	


	


	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
		return authorities;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return  true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	

	public int getNumberOfbadges() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}
