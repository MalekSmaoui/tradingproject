package tn.esprit.spring.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer>{
	

	
	User findByUsername(String username);
	
	
	User findByEmailUser(String emailUser);
	
	List<User> findByRole(Role role);



	
	
	


	


}
