package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.entity.Documentation;



public interface DocumentationRepository extends JpaRepository<Documentation, Integer>{
	 @Query("SELECT b FROM Documentation b WHERE b.titre LIKE %?1%"
	            + " OR b.descriptionD LIKE %?1%"
	            )
	    public List<Documentation> search(String keyword);
}
