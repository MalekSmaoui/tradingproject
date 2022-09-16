package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.entity.BestPractice;


public interface SpreadRepository extends JpaRepository<BestPractice, Integer> {
	 @Query("SELECT b FROM BestPractice b WHERE b.titre LIKE %?1%"
	            + " OR b.description LIKE %?1%"
	            )
	    public List<BestPractice> search(String keyword);
}
